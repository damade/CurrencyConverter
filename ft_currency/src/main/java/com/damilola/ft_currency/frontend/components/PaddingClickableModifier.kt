package com.damilola.ft_currency.frontend.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PaddingClickableModifier(
    onWhat: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onWhat() }
            .padding(all = 16.dp)
    ) {
        Text(
            text = "Hello World",
            modifier = Modifier
                .padding(all = 16.dp)
                .clickable { onWhat() }
        )
    }
}