package com.damilola.lib_compose_core.components.ui_providers

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(height: Dp) = Spacer(modifier = Modifier.height(height))

@Composable
fun HorizontalSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

fun LazyListScope.verticalSpacer(height: Dp) = item { VerticalSpacer(height = height) }

fun LazyListScope.horizontalSpacer(width: Dp) = item { HorizontalSpacer(width = width) }

@Composable
fun RowScope.FillSpaceSpacer() = Spacer(modifier = Modifier.weight(1f))

@Composable
fun ColumnScope.FillSpaceSpacer() = Spacer(modifier = Modifier.weight(1f))