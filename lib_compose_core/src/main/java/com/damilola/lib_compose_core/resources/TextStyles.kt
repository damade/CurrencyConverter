package com.damilola.lib_compose_core.resources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val ralewayThinTextStyle = TextStyle(
    fontFamily = montserratFonts,
    textAlign = TextAlign.End,
    fontWeight = FontWeight.Thin,
    fontSize = sp24
)

val montserratThinTextStyle = TextStyle(
    fontFamily = montserratFonts,
    textAlign = TextAlign.End,
    fontWeight = FontWeight.Thin,
    fontSize = sp24
)


val currencyFlagTextStyle = TextStyle(
    fontFamily = montserratFonts,
    textAlign = TextAlign.End,
    fontWeight = FontWeight.Thin,
    fontSize = sp16
)

val rateTextStyle = TextStyle(
    fontFamily = montserratFonts,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Light,
    fontSize = sp12
)

val convertButtonTextStyle = TextStyle(
    fontFamily = montserratFonts,
    textAlign = TextAlign.Center,
    color = BackgroundColor,
    fontWeight = FontWeight.Normal,
    fontSize = sp24
)


val ralewayBoldTextStyle = TextStyle(
    fontFamily = montserratFonts,
    fontWeight = FontWeight.Bold,
    color = Color.Black,
    textAlign = TextAlign.End,
    fontSize = sp18
)

val appBodyLarge = TextStyle(
    fontFamily = montserratFonts,
    fontWeight = FontWeight.Normal,
    fontSize = sp18,
    lineHeight = lineHeightSpacing24sp,
    letterSpacing = 0.5.sp
)

val display1TextStyle: TextStyle = TextStyle(
    fontSize = 30.sp,
    lineHeight = 36.sp,
    fontWeight = FontWeight.Bold,
)

val display2TextStyle: TextStyle = TextStyle(
    fontSize = 24.sp,
    lineHeight = 30.sp,
    fontWeight = FontWeight.Bold,
)

val display3TextStyle: TextStyle = TextStyle(
    fontSize = 20.sp,
    lineHeight = 24.sp,
    fontWeight = FontWeight.Bold,
)

val heading1TextStyle: TextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 20.sp,
)

val heading2TextStyle: TextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 20.sp,
)

val heading3TextStyle: TextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 20.sp,
)

val sublineTextStyle: TextStyle = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 14.sp,
    letterSpacing = 0.17.em,
)

val caption1TextStyle: TextStyle = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 14.sp,
)

val caption2TextStyle: TextStyle = TextStyle(
    fontSize = 10.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 12.sp,
)

val caption3TextStyle: TextStyle = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 14.sp,
)

val caption4TextStyle: TextStyle = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 14.sp,
    letterSpacing = -(0.04.em),
)

val body1TextStyle: TextStyle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 20.sp,
)

val body2TextStyle: TextStyle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 20.sp,
)

val buttonTextStyle: TextStyle = TextStyle(
    letterSpacing = 0.sp,
    fontSize = 16.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Medium,
)