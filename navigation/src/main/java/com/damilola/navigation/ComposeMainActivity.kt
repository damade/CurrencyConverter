package com.damilola.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.damilola.lib_compose_core.ui.theme.CurrencyConverterTheme
import com.damilola.navigation.nav_host.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeMainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                AppNavHost()
            }
        }
    }
}