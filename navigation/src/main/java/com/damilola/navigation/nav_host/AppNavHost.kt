package com.damilola.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damilola.ft_currency.util.CurrencyComposableProvider
import com.damilola.ft_currency.util.HistoryComposableProvider

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.CURRENCY_SCREEN,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.CURRENCY_SCREEN) {
            CurrencyComposableProvider(
                navigateToHistoryScreen = {
                    navController.navigate(Screens.HISTORY_SCREEN) {
                        popUpTo(Screens.CURRENCY_SCREEN) { inclusive = true }
                    }
                }
            )
        }
        composable(Screens.HISTORY_SCREEN) {
            HistoryComposableProvider()
        }
    }
}