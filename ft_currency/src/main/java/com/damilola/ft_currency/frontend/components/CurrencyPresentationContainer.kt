package com.damilola.ft_currency.frontend.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.damilola.core.ext.commaSeparator
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.core_android.R
import com.damilola.ft_currency.model.ConversionUi
import com.damilola.ft_currency.model.SymbolFlagUi
import com.damilola.lib_compose_core.ui.theme.CurrencyConverterTheme

@Composable
fun CurrencyPresentationContainer(
    fromCurrencyEditText: String,
    onFromCurrencyEditTextChange: (String) -> Unit,
    flagSpinnerList: List<SymbolFlagUi>,
    fromCurrencySpinnerState: SymbolFlagUi,
    onFromCurrencySpinnerDataChange: (SymbolFlagUi) -> Unit,
    toCurrencySpinnerDataState: SymbolFlagUi,
    onToCurrencySpinnerDataChange: (SymbolFlagUi) -> Unit,
    conversionResultState: ConversionUi,
    onConvertCurrency: () -> Unit,
    onSwapCurrency: () -> Unit,
    onClearTextField: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    )
    {

        IconButton(
            onClick = onClearTextField,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_clear_all),
                tint = com.damilola.lib_compose_core.resources.AppColour,
                contentDescription = stringResource(id = R.string.clear_text)
            )
        }

        Spacer(modifier = Modifier.size(size = com.damilola.lib_compose_core.resources.dp10))

        //From Currency
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(
                    color = com.damilola.lib_compose_core.resources.BackgroundGrey
                )
                .fillMaxWidth()
                .padding(
                    all = com.damilola.lib_compose_core.resources.dp4
                )

        ) {
            BasicTextField(
                value = fromCurrencyEditText,
                onValueChange = onFromCurrencyEditTextChange,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                maxLines = 1,
                textStyle = CurrencyConverterTheme.typography.montserratThin,
                modifier = Modifier
                    .padding(end = com.damilola.lib_compose_core.resources.dp10)
                    .weight(0.6f, true)
            )
            Text(
                text = fromCurrencySpinnerState.currencyCode,
                style = CurrencyConverterTheme.typography.currencyFlag,
                color = com.damilola.lib_compose_core.resources.CurrencyFlag,
                modifier = Modifier
                    .weight(0.2f)
            )
            Text(
                text = fromCurrencySpinnerState.currencyFlag,
                style = CurrencyConverterTheme.typography.currencyFlag,
                modifier = Modifier
                    .weight(0.1f)
            )
        }

        Spacer(modifier = Modifier.size(size = com.damilola.lib_compose_core.resources.dp20))

        //To Currency
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(
                    color = com.damilola.lib_compose_core.resources.BackgroundGrey
                )
                .fillMaxWidth()
                .padding(
                    all = com.damilola.lib_compose_core.resources.dp8
                )

        ) {
            Text(
                text = commaSeparator(
                    amount = conversionResultState.amountOfCurrencyTo
                ),
                style = CurrencyConverterTheme.typography.ralewayThin,
                modifier = Modifier
                    .padding(end = com.damilola.lib_compose_core.resources.dp10)
                    .weight(0.6f, true)
            )
            Text(
                text = toCurrencySpinnerDataState.currencyCode,
                style = CurrencyConverterTheme.typography.currencyFlag,
                color = com.damilola.lib_compose_core.resources.CurrencyFlag,
                modifier = Modifier
                    .weight(0.2f)
            )
            Text(
                text = toCurrencySpinnerDataState.currencyFlag,
                style = CurrencyConverterTheme.typography.currencyFlag,
                modifier = Modifier
                    .weight(0.1f)
            )
        }

        Spacer(modifier = Modifier.size(size = com.damilola.lib_compose_core.resources.dp20))

        //Spinner
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = com.damilola.lib_compose_core.resources.dp8
                )

        ) {

            CurrencyFlagSpinner(
                list = flagSpinnerList,
                preselected = fromCurrencySpinnerState,
                onSelectionChanged = onFromCurrencySpinnerDataChange,
                modifier = Modifier
                    .weight(weight = 0.6f)
                    .fillMaxWidth()
            )

            IconButton(
                onClick = onSwapCurrency,
                modifier = Modifier.weight(weight = 0.1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_convert),
                    contentDescription = "Swap Button"
                )
            }

            CurrencyFlagSpinner(
                list = flagSpinnerList,
                preselected = toCurrencySpinnerDataState,
                onSelectionChanged = onToCurrencySpinnerDataChange,
                modifier = Modifier
                    .weight(weight = 0.6f)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.size(size = com.damilola.lib_compose_core.resources.dp20))


        //Convert Button
        Button(
            onClick = onConvertCurrency,
            colors = ButtonDefaults.buttonColors(
                containerColor = com.damilola.lib_compose_core.resources.ConvertButtonColour
            ),
            modifier = com.damilola.lib_compose_core.resources.convertButtonModifier,
            // Uses ButtonDefaults.ContentPadding by default
            contentPadding = com.damilola.lib_compose_core.resources.paddingDp10,

            ) {
            // Inner content including an icon and a text label
            Text(
                text = stringResource(id = R.string.convert),
                style = CurrencyConverterTheme.typography.convertButton,
                modifier = Modifier
                    .padding(start = com.damilola.lib_compose_core.resources.dp10)

            )
            Spacer(
                modifier = Modifier.size(size = com.damilola.lib_compose_core.resources.dp8)
            )
            Icon(
                Icons.Filled.KeyboardArrowRight,
                tint = Color.White,
                contentDescription = "Convert",
                modifier = Modifier.size(com.damilola.lib_compose_core.resources.dp16)
            )
        }

        Spacer(modifier = Modifier.size(size = com.damilola.lib_compose_core.resources.dp20))

        AnimatedVisibility(
            visible =
            (conversionResultState.fromCurrency.isNotNullOrEmpty()
                    && conversionResultState.toCurrency.isNotNullOrEmpty()
                    && conversionResultState.rate != null
                    )
        )
        {
            if (
                conversionResultState.fromCurrency.isNotNullOrEmpty()
                && conversionResultState.toCurrency.isNotNullOrEmpty()
                && conversionResultState.rate != null
            ) {
                Text(
                    text = stringResource(
                        id = R.string.rate_result,
                        conversionResultState.rate,
                        conversionResultState.fromCurrency,
                        conversionResultState.toCurrency
                    ),
                    style = CurrencyConverterTheme.typography.rate,
                    modifier = Modifier
                        .padding(start = com.damilola.lib_compose_core.resources.dp10)
                        .fillMaxWidth()

                )
            }
        }


    }

}
