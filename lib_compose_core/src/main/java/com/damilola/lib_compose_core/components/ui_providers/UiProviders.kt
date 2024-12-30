package com.damilola.lib_compose_core.components.ui_providers

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.core.model.ErrorState
import com.damilola.core_android.R
import com.damilola.lib_compose_core.resources.dp128

@Composable
fun ShowSnackBar(message: String, snackbarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = message, block = {
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short
        )
    })
}

@Composable
fun ShowErrorMessage(errorState: ErrorState, snackbarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = errorState, block = {
        if (errorState.showError && errorState.errorMessage.isNotNullOrEmpty()) {
            snackbarHostState.showSnackbar(
                message = errorState.errorMessage!!,
                duration = SnackbarDuration.Short
            )
        }
    })
}

@Composable
fun AppLoader(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(750, easing = LinearEasing)
        ),
        label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.app_icon_splash),
        contentDescription = stringResource(id = R.string.loader),
        modifier = modifier
            .size(
                width = dp128,
                height = dp128
            )
            .graphicsLayer {
                rotationZ = angle
            }
    )
}