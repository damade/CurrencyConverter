package com.damilola.ft_currency.frontend.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.core.ext.modelConverter
import com.damilola.core.model.ErrorState
import com.damilola.core.model.UiState
import com.damilola.ft_currency.model.ConversionUi
import com.damilola.ft_currency.model.SymbolFlagSaver
import com.damilola.ft_currency.model.SymbolFlagUi


@Composable
fun CurrencyDataContainer(
    conversionUiState: UiState<Any>,
    symbolFlagUiState: UiState<Any>,
    onConvertCurrency: (
        fromCurrencySpinnerData: SymbolFlagUi,
        toCurrencySpinnerData: SymbolFlagUi,
        fromCurrencyEditText: String
    ) -> Unit,
    onSetErrorMessage: (errorMessage: String) -> Unit,
    onResetScreen: () -> Unit,
    navigateToHistoryScreen: () -> Unit
) {

    //Spinner Data
    val flagSpinnerData = modelConverter<List<SymbolFlagUi>>(symbolFlagUiState.uiStateItems)

    val flagSpinnerList = if (flagSpinnerData.isNotNullOrEmpty() && flagSpinnerData != null) {
        flagSpinnerData
    } else {
        listOf(
            SymbolFlagUi(
                currencyCode = "EUR",
                currencyFlag = "🇪🇺",
                currencyFlagUtF = "\uD83C\uDDEA\uD83C\uDDFA"
            ),
            SymbolFlagUi(
                currencyCode = "USD",
                currencyFlag = "🇺🇸",
                currencyFlagUtF = "\uD83C\uDDFA\uD83C\uDDF8"
            )
        )
    }

    var fromCurrencySpinnerData by rememberSaveable(stateSaver = SymbolFlagSaver) {
        mutableStateOf(flagSpinnerList[0])
    }
    var toCurrencySpinnerData by rememberSaveable(stateSaver = SymbolFlagSaver) {
        mutableStateOf(flagSpinnerList[0])
    }

    //Conversion Result
    val conversionRateData =
        if (modelConverter<ConversionUi>(conversionUiState.uiStateItem) != null) {
            modelConverter<ConversionUi>(conversionUiState.uiStateItem)!!
        } else {
            ConversionUi(
                fromCurrency = "",
                toCurrency = "",
                amountOfCurrencyFrom = 0,
                amountOfCurrencyTo = null,
                rate = null
            )
        }

    //Loading State
    val isLoading = symbolFlagUiState.showProgress || conversionUiState.showProgress

    //Error State
    val errorState = if (symbolFlagUiState.errorState.showError) {
        symbolFlagUiState.errorState
    } else if (conversionUiState.errorState.showError) {
        conversionUiState.errorState
    } else {
        ErrorState()
    }


    CurrencyControllerContainer(
        isLoading = isLoading,
        errorState = errorState,
        flagSpinnerList = flagSpinnerList,
        conversionRateData = conversionRateData,
        fromCurrencySpinnerData = fromCurrencySpinnerData,
        onFromCurrencySpinnerDataChange = { fromCurrencySpinnerData = it },
        toCurrencySpinnerData = toCurrencySpinnerData,
        onToCurrencySpinnerDataChange = { toCurrencySpinnerData = it },
        convertCurrency = { fromCurrencyEditText ->
            onConvertCurrency(
                fromCurrencySpinnerData,
                toCurrencySpinnerData,
                fromCurrencyEditText
            )
        },
        onSetErrorMessage = onSetErrorMessage,
        onResetScreen = onResetScreen,
        navigateToHistoryScreen = navigateToHistoryScreen
    )
}