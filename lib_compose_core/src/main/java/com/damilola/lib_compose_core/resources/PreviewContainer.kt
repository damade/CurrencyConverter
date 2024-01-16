package com.damilola.lib_compose_core.resources

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.damilola.lib_compose_core.ui.theme.CurrencyConverterTheme

@Composable
fun PreviewContainer(content: @Composable () -> Unit) {
    CurrencyConverterTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            content()
        }
    }
}