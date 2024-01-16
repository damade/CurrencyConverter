package com.damilola.navigation

import androidx.navigation.NavController
import com.damilola.core.navigation.Navigator
import com.damilola.ft_home.presentation.homeScreen.HomeFragmentDirections
import com.damilola.ft_home.presentation.splashScreen.SplashScreenFragmentDirections
import javax.inject.Inject
import javax.inject.Named

class NavigationProvider @Inject constructor(
    private val navController: NavController,
) : Navigator {

    override fun goToHomeScreenFromSplashScreen() {
        navController.navigate(
            SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment()
        )
    }

    override fun goToHistoryScreenFromHomeScreen() {
        navController.navigate(
            HomeFragmentDirections.actionHomeFragmentToConversionHistoryFragment()
        )
    }

    override fun goToComposeScreenFromHomeScreen() {
        navController.navigate(
            R.id.navigateToComposeMainActivity
        )
    }

    override fun goToHistoryScreenFromComposeScreen() {
        navController.navigate(
            com.damilola.ft_home.R.id.conversionHistoryFragment
        )
    }
}