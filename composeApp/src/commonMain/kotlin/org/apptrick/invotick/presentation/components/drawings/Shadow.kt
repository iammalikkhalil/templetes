package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawShadow(
    color: Color = Color.LightGray.copy(alpha = 0.15f),
    topLeft: Offset = Offset.Zero,
    size: Size,
    cornerRadius: Float = 0f,
    blurRadius: Float = 4f, // 4px blur as per your spec
    spread: Float = 0f, // No spread as per "0px" in your spec
    measureOnly: Boolean = false
) {
    if (measureOnly) return

    // Calculate shadow parameters
    val steps = (blurRadius.coerceAtLeast(1f)).toInt()
    val alphaStep = color.alpha / steps

    // Draw multiple concentric shapes to simulate blur
    for (i in 1..steps) {
        val currentAlpha = alphaStep * (steps - i + 1)
        val currentSpread = spread + (blurRadius * i / steps)

        val spreadSize = Size(
            width = size.width + currentSpread * 2,
            height = size.height + currentSpread * 2
        )
        val spreadOffset = topLeft - Offset(currentSpread, currentSpread)

        if (cornerRadius > 0) {
            val radius = cornerRadius + currentSpread
            drawRoundRect(
                color = color.copy(alpha = currentAlpha),
                topLeft = spreadOffset,
                size = spreadSize,
                cornerRadius = CornerRadius(radius, radius)
            )
        } else {
            drawRect(
                color = color.copy(alpha = currentAlpha),
                topLeft = spreadOffset,
                size = spreadSize
            )
        }
    }

    // Draw the main shape (transparent to reveal content beneath)
    if (cornerRadius > 0) {
        drawRoundRect(
            color = Color.Transparent,
            topLeft = topLeft,
            size = size,
            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
        )
    } else {
        drawRect(
            color = Color.Transparent,
            topLeft = topLeft,
            size = size
        )
    }
}