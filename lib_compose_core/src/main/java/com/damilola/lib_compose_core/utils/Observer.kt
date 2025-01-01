package com.damilola.lib_compose_core.utils

import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collect

suspend fun restartWhenStateChanged(
    block: () -> Unit
) {
    snapshotFlow(block)
        .collect()
}