package com.damilola.lib_compose_core.components.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp

fun Modifier.addButtonShadow(
    cornerRadiusDp: Dp,
    shadowColor: Color,
    shadowHeight: Dp,
) =
    this.drawWithContent {
        val cornerRadius = CornerRadius(cornerRadiusDp.toPx(), cornerRadiusDp.toPx())
        val roundedRectanglePath = Path().apply {
            addRoundRect(
                RoundRect(
                    left = -1f,
                    top = -shadowHeight.toPx(),
                    right = size.width + 1,
                    bottom = size.height - shadowHeight.toPx(),
                    topLeftCornerRadius = cornerRadius,
                    topRightCornerRadius = cornerRadius,
                    bottomLeftCornerRadius = cornerRadius,
                    bottomRightCornerRadius = cornerRadius
                )
            )
        }

        drawContent()
        clipPath(roundedRectanglePath, clipOp = ClipOp.Difference) {
            drawRoundRect(
                shadowColor,
                topLeft = Offset(0f, 0f),
                size = size,
                cornerRadius = cornerRadius
            )
        }
    }