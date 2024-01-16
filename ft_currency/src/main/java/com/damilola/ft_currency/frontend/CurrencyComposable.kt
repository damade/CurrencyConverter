package com.damilola.ft_currency.frontend


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.damilola.core.model.UiState
import com.damilola.ft_currency.frontend.components.CurrencyDataContainer
import com.damilola.ft_currency.frontend.viewmodel.CurrencyComposableViewModel
import com.damilola.ft_currency.model.SymbolFlagUi

@Composable
internal fun CurrencyComposable(
    currencyComposableViewModel: CurrencyComposableViewModel = hiltViewModel(),
    navigateToHistoryScreen: () -> Unit
) {

    val conversionUiState by currencyComposableViewModel.conversionUiState.collectAsState()

    val symbolFlagUiState by currencyComposableViewModel.symbolUiState.subscribeAsState(initial = UiState.Hidden)

    LaunchedEffect(Unit) {
        currencyComposableViewModel.getSymbols()
    }

    fun resetConversionResult() {
        currencyComposableViewModel.resetConversion()
    }

    fun convertCurrency(
        fromCurrencySpinnerData: SymbolFlagUi,
        toCurrencySpinnerData: SymbolFlagUi,
        fromCurrencyEditText: String
    ) {
        currencyComposableViewModel.getConversion(
            from = fromCurrencySpinnerData.currencyCode,
            fromCurrencyFlag = fromCurrencySpinnerData.currencyFlag,
            to = toCurrencySpinnerData.currencyCode,
            toCurrencyFlag = toCurrencySpinnerData.currencyFlag,
            amount = fromCurrencyEditText.toInt()
        )
    }

    fun setErrorMessage(errorMessage: String) {
        currencyComposableViewModel.setErrorMessage(
            errorMessage = errorMessage
        )
    }

    CurrencyDataContainer(
        conversionUiState = conversionUiState,
        symbolFlagUiState = symbolFlagUiState,
        onConvertCurrency = { fromCurrencySpinnerData, toCurrencySpinnerData, fromCurrencyEditText ->
            convertCurrency(
                fromCurrencySpinnerData = fromCurrencySpinnerData,
                toCurrencySpinnerData = toCurrencySpinnerData,
                fromCurrencyEditText = fromCurrencyEditText
            )
        },
        onSetErrorMessage = ::setErrorMessage,
        onResetScreen = ::resetConversionResult,
        navigateToHistoryScreen = navigateToHistoryScreen
    )


}
