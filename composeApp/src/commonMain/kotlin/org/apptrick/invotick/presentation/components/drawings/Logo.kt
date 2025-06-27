package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.drawText
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.round
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.core.scaledStyle
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.drawLogo(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    sizeDp: Float = 288f,
    measureOnly: Boolean = false,
    text: String = "LOGO",
    white: Boolean = false,
    black: Boolean = false,
): DrawBounds {
    val sizePx = ctx.scaledDpSize(sizeDp)
    val boxSize = Size(sizePx, sizePx)

    if (!measureOnly) {

        val logo =
            if (text == "LOGO") state.images.logo else if (text == "STAMP") state.images.stamp else state.images.signature

        logo?.let { image ->
            drawImageScaled(
                image = image,
                topLeft = topLeft,
                size = boxSize,
            )
        } ?: run {

            // Apply custom font family
            val originalStyle = state.style.logoLabel.copy(
                fontFamily = state.fontFamily, color = if (black) Color.White else state.color.neutral2
            )
            val scaledStyle = ctx.scaledStyle(originalStyle)

            val layoutResult = ctx.textMeasurer.measure(text, scaledStyle)
            val textSize = layoutResult.size


            // Draw grey box
            drawRect(
                color = if (white) Color.White else if (black) Color.Black else state.color.secondary, topLeft = topLeft, size = boxSize
            )

            // Center of the box
            val boxCenter = Offset(
                x = (topLeft.x + sizePx / 2f).round(), y = (topLeft.y + sizePx / 2f).round()
            )

            val textTopLeft = Offset(
                x = (boxCenter.x - textSize.width / 2f).round(),
                y = (boxCenter.y - textSize.height / 2f).round()
            )

            drawText(
                textLayoutResult = layoutResult, topLeft = textTopLeft
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = boxSize
    )
}


fun DrawScope.drawLogoLight(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    sizeDp: Float = 288f,
    measureOnly: Boolean = false,
    text: String = "LOGO",
): DrawBounds {
    val sizePx = ctx.scaledDpSize(sizeDp)
    val boxSize = Size(sizePx, sizePx)

    if (!measureOnly) {

        val logo =
            if (text == "LOGO") state.images.logo else if (text == "STAMP") state.images.stamp else state.images.signature

        logo?.let { image ->
            drawImageScaled(
                image = image,
                topLeft = topLeft,
                size = boxSize,
            )
        } ?: run {

            // Apply custom font family
            val originalStyle = state.style.logoLabel.copy(
                fontFamily = state.fontFamily, color = state.color.neutral2
            )
            val scaledStyle = ctx.scaledStyle(originalStyle)

            val layoutResult = ctx.textMeasurer.measure(text, scaledStyle)
            val textSize = layoutResult.size


            // Draw grey box
            drawRect(
                color = state.color.neutral1, topLeft = topLeft, size = boxSize
            )

            // Center of the box
            val boxCenter = Offset(
                x = (topLeft.x + sizePx / 2f).round(), y = (topLeft.y + sizePx / 2f).round()
            )

            val textTopLeft = Offset(
                x = (boxCenter.x - textSize.width / 2f).round(),
                y = (boxCenter.y - textSize.height / 2f).round()
            )

            drawText(
                textLayoutResult = layoutResult, topLeft = textTopLeft
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = boxSize
    )
}