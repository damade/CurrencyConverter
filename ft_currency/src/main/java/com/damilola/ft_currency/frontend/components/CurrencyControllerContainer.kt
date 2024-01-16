package com.damilola.ft_currency.frontend.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.damilola.core.model.ErrorState
import com.damilola.lib_compose_core.components.ui_providers.ShowErrorMessage
import com.damilola.ft_currency.model.ConversionUi
import com.damilola.ft_currency.model.SymbolFlagUi

@Composable
fun CurrencyControllerContainer(
    isLoading: Boolean,
    errorState: ErrorState,
    flagSpinnerList: List<SymbolFlagUi>,
    conversionRateData: ConversionUi,
    fromCurrencySpinnerData: SymbolFlagUi,
    onFromCurrencySpinnerDataChange: (fromCurrencySpinnerData: SymbolFlagUi) -> Unit,
    toCurrencySpinnerData: SymbolFlagUi,
    onToCurrencySpinnerDataChange: (toCurrencySpinnerData: SymbolFlagUi) -> Unit,
    convertCurrency: (
        fromCurrencyEditText: String
    ) -> Unit,
    onSetErrorMessage: (errorMessage: String) -> Unit,
    onResetScreen: () -> Unit,
    navigateToHistoryScreen: () -> Unit
) {

    //The EditText State
    var fromCurrencyEditText by rememberSaveable {
        mutableStateOf("")
    }

    fun onConvertCurrency() {
        if (fromCurrencyEditText.isEmpty()) {
            onSetErrorMessage(
                "Empty details sent"
            )
        } else if (fromCurrencyEditText.toIntOrNull() == null) {
            onSetErrorMessage(
                "Something went wrong"
            )
        } else {
            convertCurrency(
                fromCurrencyEditText
            )
        }
    }


    fun onSwapCurrency() {
        val tempSpinnerData = fromCurrencySpinnerData
        onFromCurrencySpinnerDataChange(toCurrencySpinnerData)
        onToCurrencySpinnerDataChange(tempSpinnerData)

        if (fromCurrencyEditText.toIntOrNull() != null) {
            convertCurrency(fromCurrencyEditText)
        }
    }

    fun onClearTextField() {
        fromCurrencyEditText = ""
        onResetScreen()
    }


    val snackbarHostState = remember {
        SnackbarHostState()
    }


    ShowErrorMessage(
        errorState = errorState,
        snackbarHostState = snackbarHostState
    )


    CurrencyPresenter(
        snackbarHostState = snackbarHostState,
        isLoading = isLoading,
        fromCurrencyEditText = fromCurrencyEditText,
        onFromCurrencyEditTextChange = { fromCurrencyEditText = it },
        flagSpinnerList = flagSpinnerList,
        fromCurrencySpinnerData = fromCurrencySpinnerData,
        onFromCurrencySpinnerDataChange = onFromCurrencySpinnerDataChange,
        toCurrencySpinnerData = toCurrencySpinnerData,
        onToCurrencySpinnerDataChange = onToCurrencySpinnerDataChange,
        conversionRateData = conversionRateData,
        onConvertCurrency = ::onConvertCurrency,
        onSwapCurrency = ::onSwapCurrency,
        onClearTextField = ::onClearTextField,
        navigateToHistoryScreen = navigateToHistoryScreen
    )
}