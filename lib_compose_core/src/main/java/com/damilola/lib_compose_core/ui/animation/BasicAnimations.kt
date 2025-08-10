package com.damilola.lib_compose_core.ui.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

/**
 * @see <a href="https://github.com/HedvigInsurance/android/blob/7e85f0db5781027e32700587c32c517454c95dac/app/core/core-design-system/src/main/kotlin/com/hedvig/android/core/designsystem/material3/motion/MotionDefaults.kt">source</a>
 */
object BasicAnimations {

    fun sharedXAxisEnter(density: Density): EnterTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisEnterTransition(offsetPixels)
    }

    fun sharedXAxisExit(density: Density): ExitTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisExitTransition(-offsetPixels)
    }

    fun sharedXAxisPopEnter(density: Density): EnterTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisEnterTransition(-offsetPixels)
    }

    fun sharedXAxisPopExit(density: Density): ExitTransition {
        val offsetPixels = with(density) { SharedAxisDefaults.SharedAxisOffset.dp.roundToPx() }
        return SharedAxisDefaults.sharedXAxisExitTransition(offsetPixels)
    }

    val fadeThroughEnter: EnterTransition = FadeThroughDefaults.fadeThroughEnterTransition

    val fadeThroughExit: ExitTransition = FadeThroughDefaults.fadeThroughExitTransition
}