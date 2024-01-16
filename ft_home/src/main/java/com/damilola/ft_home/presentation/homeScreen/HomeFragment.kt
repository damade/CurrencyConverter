package com.damilola.ft_home.presentation.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.damilola.core.ext.commaSeparator
import com.damilola.core.ext.ifThenDo
import com.damilola.core.ext.isNotNullOrBlank
import com.damilola.core.ext.whenNotNull
import com.damilola.core.model.ErrorState
import com.damilola.core.navigation.Navigator
import com.damilola.core_android.ext.addOnItemSelectedListener
import com.damilola.core_android.ext.lazyText
import com.damilola.core_android.ext.onBackPress
import com.damilola.core_android.ext.viewLifecycle
import com.damilola.core_android.utils.resource_providers.string
import com.damilola.core_android.utils.ui_providers.SnackBarProvider
import com.damilola.ft_home.databinding.FragmentHomeBinding
import com.damilola.ft_home.model.ConversionUi
import com.damilola.ft_home.model.HomeUiState
import com.damilola.ft_home.navigation.HomeScreenNavigationEvent
import com.damilola.ft_home.presentation.homeScreen.view_render.renderDefaultSpinner
import com.damilola.ft_home.presentation.homeScreen.view_render.renderSpinner
import com.damilola.ft_home.presentation.homeScreen.view_render.showOnLoading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding by viewLifecycle()

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var snackbar: SnackBarProvider

    private var fromCurrencyDropDownValue = ""
    private var toCurrencyDropDownValue = ""

    private var fromCurrencyDropDownPosition = 0
    private var toCurrencyDropDownPosition = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        initializeViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            homeViewModel.homeUiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::renderUiState)
        }

        //Observes State For Currency Rate Conversion
        lifecycleScope.launch {
            homeViewModel.navigationEvent
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { navigationEvent ->
                    when (navigationEvent) {
                        HomeScreenNavigationEvent.OpenComposeHomeScreen -> navigator.goToComposeScreenFromHomeScreen()
                        HomeScreenNavigationEvent.OpenHistoryScreen -> navigator.goToHistoryScreenFromHomeScreen()
                        else -> {
                            // do nothing
                        }
                    }
                }
        }
    }

    private fun renderUiState(uiState: HomeUiState) {
        binding.showOnLoading(isLoading = uiState is HomeUiState.Loading)
        when (uiState) {
            HomeUiState.Hidden, HomeUiState.Loading, HomeUiState.NoNetwork, HomeUiState.Refresh -> {}
            is HomeUiState.Conversion -> {
                setConversionResultData(data = uiState.conversion)
            }

            HomeUiState.SymbolsEmpty -> {
                binding.renderDefaultSpinner()
            }

            is HomeUiState.Error -> {
                showErrorMessage(errorState = uiState.errorState)
            }

            is HomeUiState.SymbolsLoaded -> {
                binding.renderSpinner(symbols = uiState.symbols)
            }

            HomeUiState.Reset -> {
                resetScreen()
            }
        }
    }

    private fun resetScreen() {
        binding.currencyFromEditText.text = null
        binding.currencyToEditText.text = null
        binding.rateResultView.text = null
    }

    //Set Info After Conversion
    private fun setConversionResultData(data: ConversionUi?) {
        if (data != null) {
            binding.currencyToEditText.text = commaSeparator(data.result)
            data.resultInfo.whenNotNull {
                binding.rateResultView.string = it
            }
        }
    }

    //Displays Error Message
    private fun showErrorMessage(errorState: ErrorState) {
        errorState.ifThenDo({
            it.showError && it.errorMessage.isNotNullOrBlank()
        }, {
            snackbar.showShortMessage(binding.root, it.errorMessage.orEmpty())
        })
    }

    //Initializes all views and listeners
    private fun initializeViews() {
        val currencyToTextView = binding.currencyToTextView

        val currencyFromTextView = binding.currencyFromTextView

        binding.goToHistory.setOnClickListener {
            homeViewModel.navigateToHistoryScreen()
        }

        binding.seeNewScreenView.setOnClickListener {
            homeViewModel.navigateToComposeHomeScreen()
        }

        this.onBackPress {
            requireActivity().finish()
        }

        binding.toSpinner.addOnItemSelectedListener(
            onItemSelectedPositionAndText = { position, text ->
                toCurrencyDropDownPosition = position
                toCurrencyDropDownValue = text
                currencyToTextView.text = text
            },
        )

        binding.fromSpinner.addOnItemSelectedListener(
            onItemSelectedPositionAndText = { position, text ->
                fromCurrencyDropDownPosition = position
                fromCurrencyDropDownValue = text
                currencyFromTextView.text = text
            },
        )

        //Clear All Input and Output Fields
        binding.clearButton.setOnClickListener { homeViewModel.onReset() }

        //Calls the convert method from repository
        binding.convertButton.setOnClickListener { onCurrencyConversion() }

        //Setting Reversal Button
        binding.swapButton.setOnClickListener { onSwapCurrency() }
    }

    private fun onCurrencyConversion() {
        val amountString = binding.currencyFromEditText.lazyText()
        val fromCurrency = fromCurrencyDropDownValue
        val toCurrency = toCurrencyDropDownValue

        logcat("Amount") { amountString }
        homeViewModel.onCurrencyConversion(from = fromCurrency, to = toCurrency, amount = amountString)
    }

    private fun onSwapCurrency() {
        val amountString = binding.currencyFromEditText.lazyText()
        val fromCurrency = toCurrencyDropDownValue
        val toCurrency = fromCurrencyDropDownValue

        //Set Temporary Position
        val fromCurrencySpinnerPosition = fromCurrencyDropDownPosition
        val toCurrencySpinnerPosition = toCurrencyDropDownPosition
        //Set Position
        fromCurrencyDropDownPosition = toCurrencySpinnerPosition
        toCurrencyDropDownPosition = fromCurrencySpinnerPosition

        binding.fromSpinner.setSelection(toCurrencySpinnerPosition, true)
        binding.toSpinner.setSelection(fromCurrencySpinnerPosition, true)

        logcat("Amount") { amountString }
        homeViewModel.onCurrencyConversion(fromCurrency, toCurrency, amountString)
    }

}