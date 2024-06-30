package com.damilola.ft_home.presentation.historyScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.damilola.core.ext.*
import com.damilola.core.model.ErrorState
import com.damilola.core.model.NavigateToPreviousScreen
import com.damilola.core_android.ext.bindObserver
import com.damilola.core_android.ext.onBackPress
import com.damilola.core_android.ext.show
import com.damilola.core_android.ext.showErrorMessage
import com.damilola.core_android.ext.startAnim
import com.damilola.core_android.ext.stopAnim
import com.damilola.core_android.ext.viewLifecycle
import com.damilola.core_android.utils.resource_providers.StringResource
import com.damilola.core_android.utils.resource_providers.string
import com.damilola.core_android.utils.ui_providers.SnackBarProvider
import com.damilola.ft_home.databinding.FragmentConversionHistoryBinding
import com.damilola.ft_home.model.ConversionUi
import com.damilola.ft_home.presentation.historyScreen.adapter.ConversionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ConversionHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ConversionHistoryFragment : Fragment() {

    private var binder: FragmentConversionHistoryBinding by viewLifecycle()
    private val conversionHistoryViewModel: ConversionHistoryViewModel by viewModels()
    private lateinit var conversionAdapter: ConversionAdapter

    @Inject
    lateinit var navigateToPreviousScreen: NavigateToPreviousScreen

    @Inject
    lateinit var snackbar: SnackBarProvider


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binder = FragmentConversionHistoryBinding.inflate(layoutInflater)

        // Inflate
        initView()

        this.onBackPress {
            navigateToPreviousScreen()
        }

        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Observes State For Currency Conversion History Done
        lifecycleScope.launch {
            conversionHistoryViewModel.uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect { currentUiState ->
                    binder.conversionShimmerLayout.stopAnim()
                    showOnLoading(currentUiState.showProgress)
                    showEmptyState(currentUiState.showEmpty)
                    showErrorMessage(currentUiState.errorState)
                    showOnRefresh(currentUiState.showOnRefresh)
                    setConversationHistoryData(modelConverter<List<ConversionUi>>(currentUiState.uiStateItems))
                }
        }

    }

    // Initializing the RecyclerView
    private fun initView() {
        conversionAdapter = ConversionAdapter()
        binder.itemRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = conversionAdapter
            bindObserver(binder.emptyState.root)
        }

        binder.emptyState.emptyStateMessage.string = StringResource(res = com.damilola.core_android.R.string.item_empty_text)
    }

    // Sets All Currency Conversion History List
    private fun setConversationHistoryData(data: List<ConversionUi>?) {
        if (data.isNotNullOrEmpty() && data != null) {

            // Show Clear History Button
            binder.clearButton.visibility = View.VISIBLE

            binder.clearButton.setOnClickListener {
                conversionHistoryViewModel.clearConversionHistories()
            }

            conversionAdapter.submitList(data)
        }
    }

    // Displays Error Message
    private fun showErrorMessage(errorState: ErrorState) {
        if (errorState.showError) {
            setOnRetryListener()
            errorState.errorMessage?.let { snackbar.showShortMessage(binder.root, it) }
            errorState.errorMessage?.let { binder.errorState.showErrorMessage(it) }
        } else {
            binder.errorState.root.visibility = View.GONE
        }
    }

    // Displays Empty Layout when History List is Empty
    private fun showEmptyState(visible: Boolean) {
        binder.emptyState.root.show = visible
    }

    // Indicates That Data Has Been Fetched
    private fun showOnLoading(isLoading: Boolean) {
        if (isLoading) {
            onRefresh()
            binder.conversionShimmerLayout.startAnim()
        } else {
            binder.conversionShimmerLayout.stopAnim()
        }
    }

    // Indicates That Data Has Been Refreshed
    private fun showOnRefresh(isRefreshing: Boolean) {
        if (isRefreshing) {
            binder.refreshBtn.isRefreshing = false
        }
    }

    // Setting listener to attempt to load data if there is a failure
    private fun setOnRetryListener() {
        binder.errorState.errorRefresh.setOnClickListener {
            conversionHistoryViewModel.getConversionHistories()
        }
    }

    // Setting listener for refreshing data again
    private fun onRefresh() {
        binder.refreshBtn.setOnRefreshListener {
            conversionHistoryViewModel.onSwipeRefresh()
        }
    }


}