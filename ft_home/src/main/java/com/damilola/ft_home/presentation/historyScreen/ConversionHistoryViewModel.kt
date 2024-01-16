package com.damilola.ft_home.presentation.historyScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damade.lib_currency_search.domain.usecase.conversion.ClearConversionHistory
import com.damade.lib_currency_search.domain.usecase.conversion.GetConversionHistory
import com.damilola.core.ext.applyUpdate
import com.damilola.core.ext.errorMessage
import com.damilola.core.model.toErrorState
import com.damilola.core.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
internal class ConversionHistoryViewModel @Inject constructor(
    private val getConversionHistory: GetConversionHistory,
    private val clearConversionHistory: ClearConversionHistory,
    private val conversionDomainMapper: com.damilola.ft_home.mapper.ConversionDomainMapper,
) : ViewModel() {

    private val mutableUiState = MutableStateFlow(UiState.Hidden)
    val uiState: StateFlow<UiState<Any>> = mutableUiState.asStateFlow()

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {

        getConversionHistories()
    }

    internal fun onSwipeRefresh() {
        try {
            mutableUiState.applyUpdate {
                copy(showProgress = true, showOnRefresh = true)
            }
            getConversionHistories()
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableUiState.applyUpdate {
                    copy(errorState = t.errorMessage.toErrorState())
                }
            } else {
                throw t
            }
        }
    }

    internal fun getConversionHistories() {
        try {
            viewModelScope.launch {
                getConversionHistory().onStart {
                    mutableUiState.applyUpdate {
                        copy(showProgress = true)
                    }
                }.catch { error ->
                    run {
                        mutableUiState.applyUpdate {
                            copy(errorState = error.errorMessage.toErrorState())
                        }
                    }
                }.map {
                    conversionDomainMapper.mapFromDomainList(it)
                }.collect { conversionHistories ->
                    run {
                        mutableUiState.applyUpdate {
                            copy(
                                showEmpty = conversionHistories.isEmpty(),
                                uiStateItems = conversionHistories,
                            )
                        }
                    }
                }
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableUiState.applyUpdate {
                    copy(errorState = t.errorMessage.toErrorState())
                }
            } else {
                throw t
            }
        }
    }

    internal fun clearConversionHistories() {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    clearConversionHistory.execute()
                    getConversionHistories()
                }
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableUiState.applyUpdate {
                    copy(errorState = t.errorMessage.toErrorState())
                }
            } else {
                throw t
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}