package com.damilola.ft_currency.frontend.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.damilola.core_android.R
import com.damilola.ft_currency.model.ConversionUi
import com.damilola.ft_currency.model.SymbolFlagUi
import com.damilola.lib_compose_core.components.appbar.AppBarCurrencyComposable
import com.damilola.lib_compose_core.resources.PreviewContainer
import kotlin.random.Random

@Composable
fun CurrencyPresenter(
    snackbarHostState: SnackbarHostState,
    isLoading: Boolean,
    fromCurrencyEditText: String,
    onFromCurrencyEditTextChange: (String) -> Unit,
    flagSpinnerList: List<SymbolFlagUi>,
    fromCurrencySpinnerData: SymbolFlagUi,
    onFromCurrencySpinnerDataChange: (SymbolFlagUi) -> Unit,
    toCurrencySpinnerData: SymbolFlagUi,
    onToCurrencySpinnerDataChange: (SymbolFlagUi) -> Unit,
    conversionRateData: ConversionUi,
    onConvertCurrency: () -> Unit,
    onSwapCurrency: () -> Unit,
    onClearTextField: () -> Unit,
    navigateToHistoryScreen: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AppBarCurrencyComposable(navigateUp = navigateToHistoryScreen)
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(all = com.damilola.lib_compose_core.resources.dp20)
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                Text(
                    text = stringResource(id = R.string.currency_calculator_dot),
                    color = com.damilola.lib_compose_core.resources.AppColour,
                    fontSize = 32.sp,
                )

                CurrencyPresentationContainer(
                    fromCurrencyEditText = fromCurrencyEditText,
                    onFromCurrencyEditTextChange = onFromCurrencyEditTextChange,
                    flagSpinnerList = flagSpinnerList,
                    fromCurrencySpinnerState = fromCurrencySpinnerData,
                    onFromCurrencySpinnerDataChange = onFromCurrencySpinnerDataChange,
                    toCurrencySpinnerDataState = toCurrencySpinnerData,
                    onToCurrencySpinnerDataChange = onToCurrencySpinnerDataChange,
                    conversionResultState = conversionRateData,
                    onConvertCurrency = onConvertCurrency,
                    onSwapCurrency = onSwapCurrency,
                    onClearTextField = onClearTextField,

                    )
            }

            if (isLoading) {
                com.damilola.lib_compose_core.components.ui_providers.AppLoader(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview()
@Composable
private fun CurrencyPresenterPreview() {
    val conversionUi = ConversionUi(
        fromCurrency = "GBP",
        toCurrency = "NGN",
        amountOfCurrencyFrom = 10,
        amountOfCurrencyTo = (15_000).toDouble(),
        rate = (1_500).toDouble()
    )
    PreviewContainer {
        CurrencyPresenter(
            snackbarHostState = SnackbarHostState(),
            isLoading = Random.nextBoolean(),
            fromCurrencyEditText = "1000",
            onFromCurrencyEditTextChange = {},
            flagSpinnerList = flagSpinnerList,
            fromCurrencySpinnerData = flagSpinnerList.first(),
            onFromCurrencySpinnerDataChange = {},
            toCurrencySpinnerData = flagSpinnerList.last(),
            onToCurrencySpinnerDataChange = {},
            conversionRateData = conversionUi,
            onConvertCurrency = {},
            onSwapCurrency = {},
            onClearTextField = {},
            navigateToHistoryScreen = {},
        )
    }
}