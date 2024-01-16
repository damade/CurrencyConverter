package com.damilola.lib_compose_core.resources

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class CurrencyConverterTypography(
    val ralewayThin: TextStyle = ralewayThinTextStyle,
    val ralewayBold: TextStyle = ralewayBoldTextStyle,
    val montserratThin: TextStyle = montserratThinTextStyle,
    val currencyFlag: TextStyle = currencyFlagTextStyle,
    val rate: TextStyle = rateTextStyle,
    val convertButton: TextStyle = convertButtonTextStyle,
    val appBody: TextStyle = appBodyLarge,
    val display1: TextStyle = display1TextStyle,
    val display2: TextStyle = display2TextStyle,
    val display3: TextStyle = display3TextStyle,
    val heading1: TextStyle = heading1TextStyle,
    val heading2: TextStyle = heading2TextStyle,
    val heading3: TextStyle = heading3TextStyle,
    val subline: TextStyle = sublineTextStyle,
    val caption1: TextStyle = caption1TextStyle,
    val caption2: TextStyle = caption2TextStyle,
    val caption3: TextStyle = caption3TextStyle,
    val caption4: TextStyle = caption4TextStyle,
    val body1: TextStyle = body1TextStyle,
    val body2: TextStyle = body2TextStyle,
    val button: TextStyle = buttonTextStyle,
)

val LocalCurrencyConverterTypography = staticCompositionLocalOf { CurrencyConverterTypography() }