package com.damilola.ft_home.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionQuery
import com.damade.lib_currency_search.domain.usecase.conversion.GetConversionRate
import com.damade.lib_currency_search.domain.usecase.symbol.GetSymbol
import com.damilola.core.ext.applyUpdate
import com.damilola.core.ext.errorMessage
import com.damilola.core.model.toErrorState
import com.damilola.ft_home.mapper.ConversionDomainMapper
import com.damilola.ft_home.mapper.SymbolDomainMapper
import com.damilola.ft_home.model.HomeUiState
import com.damilola.ft_home.navigation.HomeScreenNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getConversionRate: GetConversionRate,
    private val getSymbol: GetSymbol,
    private val conversionDomainMapper: ConversionDomainMapper,
    private val symbolDomainMapper: SymbolDomainMapper,
) : ViewModel() {

    private val mutableHomeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Hidden)
    val homeUiState: StateFlow<HomeUiState> = mutableHomeUiState.asStateFlow()

    private val mutableNavigationEvent: MutableStateFlow<HomeScreenNavigationEvent> =
        MutableStateFlow(HomeScreenNavigationEvent.Hidden)
    val navigationEvent: StateFlow<HomeScreenNavigationEvent> = mutableNavigationEvent.asStateFlow()

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        getSymbols()
    }

    internal fun navigateToHistoryScreen() {
        mutableNavigationEvent.applyUpdate {
            HomeScreenNavigationEvent.OpenHistoryScreen
        }
    }

    internal fun navigateToComposeHomeScreen() {
        mutableNavigationEvent.applyUpdate {
            HomeScreenNavigationEvent.OpenComposeHomeScreen
        }
    }

    internal fun onReset() {
        mutableHomeUiState.applyUpdate {
            HomeUiState.Reset
        }
    }


    private fun getSymbols() {
        try {
            viewModelScope.launch {
                getSymbol().onStart {
                    mutableHomeUiState.applyUpdate {
                        HomeUiState.Loading
                    }
                }.catch { error ->
                    mutableHomeUiState.applyUpdate {
                        HomeUiState.Error(errorState = error.errorMessage.toErrorState())
                    }
                }.map {
                    symbolDomainMapper.mapFromDomainList(it)
                }.collect { symbols ->
                    mutableHomeUiState.applyUpdate {
                        if (symbols.isEmpty()) {
                            HomeUiState.SymbolsEmpty
                        } else {
                            HomeUiState.SymbolsLoaded(symbols = symbols)
                        }
                    }
                }
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableHomeUiState.applyUpdate {
                    HomeUiState.Error(errorState = t.errorMessage.toErrorState())
                }
            } else {
                throw t
            }
        }
    }

    internal fun onCurrencyConversion(from: String, to: String, amount: String) {
        try {
            viewModelScope.launch {
                mutableHomeUiState.applyUpdate {
                    HomeUiState.Loading
                }
                if (amount.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    mutableHomeUiState.applyUpdate {
                        HomeUiState.Error(errorState = "Empty details sent".toErrorState())
                    }
                } else if (from == to) {
                    val conversion = Conversion(
                        from = from,
                        to = to,
                        amount = amount.toInt(),
                        rate = 1.0,
                        result = amount.toDouble(),
                    )
                    val conversionUi = conversionDomainMapper.mapFromDomain(domain = conversion)
                    mutableHomeUiState.applyUpdate {
                        HomeUiState.Conversion(conversion = conversionUi)
                    }
                } else {
                    val conversionQuery = ConversionQuery(from, to, amount.toInt())
                    getConversion(conversionQuery = conversionQuery)
                }
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableHomeUiState.applyUpdate {
                    HomeUiState.Error(errorState = t.errorMessage.toErrorState())
                }
            } else {
                throw t
            }
        }
    }

    private suspend fun getConversion(conversionQuery: ConversionQuery) {
        try {
            getConversionRate(conversionQuery).catch { error ->
                mutableHomeUiState.applyUpdate {
                    HomeUiState.Error(errorState = error.errorMessage.toErrorState())
                }
            }.map(conversionDomainMapper::mapFromDomain).collect { conversion ->
                mutableHomeUiState.applyUpdate {
                    HomeUiState.Conversion(conversion = conversion)
                }
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                mutableHomeUiState.applyUpdate {
                    HomeUiState.Error(errorState = t.errorMessage.toErrorState())
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