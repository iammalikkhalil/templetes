package org.apptrick.invotick.core

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import org.apptrick.invotick.core.model.DrawBounds

fun DrawScope.drawStyledText(
    text: String,
    topLeft: Offset,
    originalStyle: TextStyle,
    ctx: InvoiceRenderContext,
    measureOnly: Boolean = false
): DrawBounds {
    val scaledFontSizePx = with(ctx.density) {
        originalStyle.fontSize.toPx()
    } * ctx.scale

    val scaledStyle = originalStyle.copy(
//        fontSize = scaledFontSizePx.toSp()
    )

    val layoutResult = ctx.textMeasurer.measure(text, scaledStyle)

    if (!measureOnly) {
        drawText(
            textLayoutResult = layoutResult,
            topLeft = topLeft
        )
    }
    return DrawBounds(
        topLeft = topLeft,
        size = Size(
            width = layoutResult.size.width.toFloat(),
            height = layoutResult.size.height.toFloat()
        )
    )
}

fun DrawScope.drawStyledHeading(
    text: String,
    topLeft: Offset,
    originalStyle: TextStyle,
    ctx: InvoiceRenderContext,
    measureOnly: Boolean = false
): DrawBounds {
    val scaledStyle = ctx.scaledStyle(originalStyle)

    val layoutResult = ctx.textMeasurer.measure(text, scaledStyle)

    if (!measureOnly) {
        drawText(
            textLayoutResult = layoutResult,
            topLeft = topLeft
        )
    }
    return DrawBounds(
        topLeft = topLeft,
        size = Size(
            width = layoutResult.size.width.toFloat(),
            height = layoutResult.size.height.toFloat()
        )
    )
}


