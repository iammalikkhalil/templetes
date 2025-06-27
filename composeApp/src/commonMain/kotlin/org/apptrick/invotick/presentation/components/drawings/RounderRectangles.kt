package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.model.DrawBounds

fun DrawScope.drawBottomRoundedRect(
    color: Color,
    topLeft: Offset,
    size: Size,
    cornerRadius: Float,
    measureOnly: Boolean = false
): DrawBounds {
    if (!measureOnly) {
        val left = topLeft.x
        val top = topLeft.y
        val right = left + size.width
        val bottom = top + size.height

        val path = Path().apply {
            moveTo(left, top)
            lineTo(right, top)
            lineTo(right, bottom - cornerRadius)

            // Bottom-right arc
            arcTo(
                rect = Rect(
                    right - 2 * cornerRadius,
                    bottom - 2 * cornerRadius,
                    right,
                    bottom
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Bottom-left arc
            lineTo(left + cornerRadius, bottom)
            arcTo(
                rect = Rect(
                    left,
                    bottom - 2 * cornerRadius,
                    left + 2 * cornerRadius,
                    bottom
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(left, top)
            close()
        }

        drawPath(path, color = color)
    }

    return DrawBounds(topLeft, size)
}


fun DrawScope.drawTopRoundedRect(
    color: Color,
    topLeft: Offset,
    size: Size,
    cornerRadius: Float,
    measureOnly: Boolean = false
): DrawBounds {
    if (!measureOnly) {
        val left = topLeft.x
        val top = topLeft.y
        val right = left + size.width
        val bottom = top + size.height

        val path = Path().apply {
            moveTo(left + cornerRadius, top)
            lineTo(right - cornerRadius, top)

            // Top-right arc
            arcTo(
                rect = Rect(
                    right - 2 * cornerRadius,
                    top,
                    right,
                    top + 2 * cornerRadius
                ),
                startAngleDegrees = -90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(right, bottom)
            lineTo(left, bottom)

            // Top-left arc
            lineTo(left, top + cornerRadius)
            arcTo(
                rect = Rect(
                    left,
                    top,
                    left + 2 * cornerRadius,
                    top + 2 * cornerRadius
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            close()
        }

        drawPath(path, color = color)
    }

    return DrawBounds(topLeft, size)
}
