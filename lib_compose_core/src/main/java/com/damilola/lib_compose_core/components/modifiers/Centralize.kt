package com.damilola.lib_compose_core.components.modifiers

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.centerHorizontally(): Modifier = this.fillMaxWidth().wrapContentWidth()

@Composable
fun Modifier.centerVertically(): Modifier = this.fillMaxHeight().wrapContentHeight()