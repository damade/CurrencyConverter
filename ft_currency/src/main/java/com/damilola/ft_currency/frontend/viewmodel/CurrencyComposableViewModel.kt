package com.damilola.ft_currency.frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damade.lib_currency.domain.usecase.GetSymbolFlags
import com.damade.lib_currency_search.domain.model.ConversionWithFlagsQuery
import com.damade.lib_currency_search.domain.usecase.conversion.GetConversionWithFlagRate
import com.damilola.core.ext.applyUpdate
import com.damilola.core.ext.errorMessage
import com.damilola.core.ext.update
import com.damilola.core.model.NetworkMiddlewareFailure
import com.damilola.core.model.UiState
import com.damilola.core.model.toErrorState
import com.damilola.ft_currency.mapper.ConversionDomainMapper
import com.damilola.ft_currency.mapper.SymbolFlagDomainMapper
import com.jakewharton.rxrelay3.BehaviorRelay
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class CurrencyComposableViewModel @Inject constructor(
    private val getConversionWithFlagRate: GetConversionWithFlagRate,
    private val getSymbolFlags: GetSymbolFlags,
    private val conversionDomainMapper: ConversionDomainMapper,
    private val symbolFlagDomainMapper: SymbolFlagDomainMapper
) : ViewModel() {

    private val mutableSymbolUiState = BehaviorRelay.createDefault(UiState.Hidden)

    //private val mutableSymbolUiState = PublishRelay.create<UiState<Any>>()
    val symbolUiState: Observable<UiState<Any>> = mutableSymbolUiState

    private val mutableConversionUiState = MutableStateFlow(UiState.Hidden)
    val conversionUiState: StateFlow<UiState<Any>> = mutableConversionUiState.asStateFlow()

    private val internalDisposables by lazy { CompositeDisposable() }

    internal fun getSymbols() {
        internalDisposables.add(
            getSymbolFlags
                .execute()
                .filter { it.getSuccessOrNull() != null || it.getFailureOrNull() != null }
                .map { response ->
                    if (response.isSuccess) {
                        val symbols = symbolFlagDomainMapper.mapFromDomainList(response.getSuccessNonNull())
                        mutableSymbolUiState.update {
                            copy(uiStateItems = symbols, showProgress = false)
                        }
                    } else {
                        val error = response.getFailureNonNull()
                        if (error is NetworkMiddlewareFailure) {
                            mutableSymbolUiState.update {
                                copy(showNoNetwork = true, showProgress = false)
                            }
                        } else {
                            mutableSymbolUiState.update {
                                copy(errorState = error.errorMessage.toErrorState(), showProgress = false)
                            }
                        }
                    }
                }.startWith(
                    Observable.just(Unit).map {
                        mutableSymbolUiState.update {
                            copy(showProgress = true)
                        }
                    }
                ).onErrorReturn { error ->
                    mutableSymbolUiState.update {
                        copy(errorState = error.errorMessage.toErrorState(), showProgress = false)
                    }
                }.subscribe()
        )


    }

    private fun testCode() {
        mutableConversionUiState.stateIn(
            scope = viewModelScope,
            // Timeout is now 0!
            started = SharingStarted.WhileSubscribed(),
            initialValue = UiState.Hidden,
        )
    }

    internal fun getConversion(
        from: String, fromCurrencyFlag: String,
        to: String, toCurrencyFlag: String, amount: Int
    ) {
        try {
            viewModelScope.launch {
                val conversionQuery = ConversionWithFlagsQuery(
                    from = from,
                    fromCurrencyFlag = fromCurrencyFlag,
                    to = to, toCurrencyFlag = toCurrencyFlag,
                    amount = amount
                )
                getConversionWithFlagRate(conversionQuery).onStart {
                    mutableConversionUiState.applyUpdate {
                        copy(showProgress = true)
                    }
                }.catch { error ->
                    mutableConversionUiState.applyUpdate {
                        copy(errorState = error.errorMessage.toErrorState())
                    }
                }.map {
                    conversionDomainMapper.mapFromDomain(it)
                }.collect { conversion ->
                    mutableConversionUiState.applyUpdate {
                        copy(uiStateItem = conversion)
                    }
                }
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableConversionUiState.applyUpdate {
                    copy(errorState = t.errorMessage.toErrorState())
                }
            } else {
                throw t
            }
        }
    }

    internal fun setErrorMessage(errorMessage: String) {
        try {
            mutableConversionUiState.applyUpdate {
                copy(errorState = errorMessage.toErrorState())
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableConversionUiState.applyUpdate {
                    copy(errorState = t.errorMessage.toErrorState())
                }
            } else {
                throw t
            }
        }
    }

    internal fun resetConversion() {
        try {
            mutableConversionUiState.update {
                UiState.Hidden
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableConversionUiState.applyUpdate {
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
        getSymbolFlags.clear()
        internalDisposables.clear()
    }

}