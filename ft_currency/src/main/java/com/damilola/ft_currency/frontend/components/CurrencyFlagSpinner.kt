package com.damilola.ft_currency.frontend.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.damilola.ft_currency.model.SymbolFlagUi
import com.damilola.lib_compose_core.resources.PreviewContainer
import com.damilola.lib_compose_core.ui.theme.CurrencyConverterTheme

@Composable
fun CurrencyFlagSpinner(
    list: List<SymbolFlagUi>,
    preselected: SymbolFlagUi,
    onSelectionChanged: (symbolFlagUi: SymbolFlagUi) -> Unit,
    modifier: Modifier = Modifier,
) {
    // initial value
    //var selected by remember { mutableStateOf(preselected) }
    var selected = preselected
    var expanded by remember { mutableStateOf(false) } // initial value

    OutlinedCard(
        modifier = modifier.clickable {
            expanded = !expanded
        }
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = com.damilola.lib_compose_core.resources.dp8),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {

            Text(
                text = selected.currencyFlag,
                style = CurrencyConverterTheme.typography.currencyFlag,
                color = com.damilola.lib_compose_core.resources.CurrencyFlag,
                modifier = Modifier
                    .weight(0.1f)
                    .padding(all = com.damilola.lib_compose_core.resources.dp8)
            )
            Text(
                text = selected.currencyCode,
                style = CurrencyConverterTheme.typography.currencyFlag,
                modifier = Modifier
                    .weight(0.2f)
                    .padding(all = com.damilola.lib_compose_core.resources.dp8)
            )

            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))

        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentWidth()   // delete this modifier and use .wrapContentWidth() if you would like to wrap the dropdown menu around the content
        ) {
            list.forEach { listEntry ->

                DropdownMenuItem(
                    onClick = {
                        selected = listEntry
                        expanded = false
                        onSelectionChanged(selected)
                    },
                    text = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = com.damilola.lib_compose_core.resources.dp8),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top,
                        ) {
                            Text(
                                text = listEntry.currencyFlag,
                                modifier = Modifier
                                    .padding(end = com.damilola.lib_compose_core.resources.dp8)
                                    .wrapContentWidth()  //optional instad of fillMaxWidth
                            )
                            Text(
                                text = listEntry.currencyCode,
                                modifier = Modifier
                                    .padding(start = com.damilola.lib_compose_core.resources.dp4)
                                    .wrapContentWidth()  //optional instad of fillMaxWidth
                            )
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FlagSpinnerDefaultPreview() {

    var fromCurrecySpinnerData: SymbolFlagUi?

    PreviewContainer {
        CurrencyFlagSpinner(
            list = flagSpinnerList,
            preselected = flagSpinnerList.first(),
            onSelectionChanged = { fromCurrecySpinnerData = it },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

internal val flagSpinnerList
    get() = listOf(
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