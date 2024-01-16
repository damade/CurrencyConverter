package com.damilola.ft_currency.util

import androidx.compose.runtime.Composable
import com.damilola.ft_currency.frontend.CurrencyComposable
import com.damilola.ft_currency.frontend.components.HistoryScreen

@Composable
fun CurrencyComposableProvider(navigateToHistoryScreen: () -> Unit) = CurrencyComposable(
   navigateToHistoryScreen =  navigateToHistoryScreen
)

@Composable
fun HistoryComposableProvider() = HistoryScreen()