package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.drawText
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.core.scaledStyle
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.drawInvoiceDetail(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    measureOnly: Boolean = false,
    lightMode: Boolean = false,
): DrawBounds {
    val headingStyle = ctx.scaledStyle(state.style.detailLabel.copy(fontFamily = state.fontFamily, color = if (lightMode) Color.White else state.color.neutral2))
    val valueStyle = ctx.scaledStyle(state.style.detailText.copy(fontFamily = state.fontFamily, color = if (lightMode) Color.White else state.color.neutral2))

    val rows = state.data.detail

    val maxLabelWidth = rows.maxOf {
        val labelLayout = ctx.textMeasurer.measure(it.first, headingStyle)
        labelLayout.getLineRight(0)
    }

    val colGap = ctx.scaledDpSize(80f)

    val valueX = topLeft.x + maxLabelWidth + colGap

    var currentY = topLeft.y
    var maxWidth = 0f

    for ((label, value) in rows) {
        val labelLayout = ctx.textMeasurer.measure(label, headingStyle)
        val valueLayout = ctx.textMeasurer.measure(value, valueStyle)

        maxWidth = maxOf(maxWidth, (valueX + valueLayout.size.width) - topLeft.x)

        if (!measureOnly) {
            drawText(
                textLayoutResult = labelLayout,
                topLeft = Offset(topLeft.x, currentY)
            )

            drawText(
                textLayoutResult = valueLayout,
                topLeft = Offset(valueX, currentY + labelLayout.size.height * 0.1f)
            )
        }

        currentY += labelLayout.size.height
    }

    return DrawBounds(
        topLeft = topLeft,
        size = Size(
            width = maxWidth,
            height = currentY - topLeft.y
        )
    )
}





