package com.damilola.ft_currency.frontend.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestModifier() {
    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .clickable { }
    ) {
    }
}