package com.damilola.lib_compose_core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.damilola.lib_compose_core.resources.CurrencyConverterTypography
import com.damilola.lib_compose_core.resources.LocalCurrencyConverterTypography
import com.damilola.lib_compose_core.ui.typography.Typography

private val DarkColorScheme = darkColorScheme(
    primary = com.damilola.lib_compose_core.resources.Purple80,
    secondary = com.damilola.lib_compose_core.resources.PurpleGrey80,
    tertiary = com.damilola.lib_compose_core.resources.Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = com.damilola.lib_compose_core.resources.Purple40,
    secondary = com.damilola.lib_compose_core.resources.PurpleGrey40,
    tertiary = com.damilola.lib_compose_core.resources.Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CurrencyConverterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(LocalCurrencyConverterTypography provides CurrencyConverterTypography()) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object CurrencyConverterTheme {
    val typography: CurrencyConverterTypography
        @Composable get() = LocalCurrencyConverterTypography.current

    val colors: ColorScheme
        @Composable get() = MaterialTheme.colorScheme
}