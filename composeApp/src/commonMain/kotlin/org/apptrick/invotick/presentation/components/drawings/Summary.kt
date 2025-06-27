package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.drawText
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.core.scaledStyle
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.drawInvoiceSummary(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    width: Float,
    measureOnly: Boolean = false,
    hideBorder: Boolean = false,
    horizontalPaddingDp: Float = 0f,
    textPaddingDp: Float = 50f,
    verticalPaddingDp: Float = 0f,
    rowSpacingDp: Float = 55f,
    borderStrokeDp: Float = 2f,
    lastRowBackgroundHeightDp: Float = 60f,
    hideSummaryTotalBg: Boolean = false
): DrawBounds {
    val labelStyle = ctx.scaledStyle(
        state.style.summaryLabel.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )


    val valueStyle = ctx.scaledStyle(
        state.style.summaryValue.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )

    val labelStyleLast = ctx.scaledStyle(
        state.style.summaryLastRow.copy(
            fontFamily = state.fontFamily, color = state.color.neutral1
        )
    )

    val lastRowBackground = state.color.primary
    val borderColor = state.color.neutral3

    val tPadding = ctx.scaledDpSize(textPaddingDp)
    val hPadding = ctx.scaledDpSize(horizontalPaddingDp)
    val vPadding = ctx.scaledDpSize(verticalPaddingDp)
    val stroke = ctx.scaledDpSize(borderStrokeDp)
    val rowSpacing = ctx.scaledDpSize(rowSpacingDp)
    val lastRowBackgroundHeight = ctx.scaledDpSize(lastRowBackgroundHeightDp)

    val rows = state.data.summary

    var currentY = topLeft.y + vPadding

    rows.forEachIndexed { index, (label, value) ->
        val isLast = index == rows.lastIndex
        val isSecondLast = index == rows.lastIndex - 1

        val labelTextStyle = if (isLast) if (!hideSummaryTotalBg) labelStyleLast else labelStyle else labelStyle
        val valueTextStyle = if (isLast) if (!hideSummaryTotalBg) labelStyleLast else labelStyle else valueStyle
//        val labelTextStyle = if (isLast) labelStyleLast else labelStyle
//        val valueTextStyle = if (isLast) labelStyleLast else valueStyle

        val labelLayout = ctx.textMeasurer.measure(label, labelTextStyle)
        val valueLayout = ctx.textMeasurer.measure(value, valueTextStyle)

        val lineHeight = maxOf(labelLayout.size.height, valueLayout.size.height)
        val labelY = currentY + (lineHeight - labelLayout.size.height) / 2f
        val valueY = currentY + (lineHeight - valueLayout.size.height) / 2f

        val labelX = topLeft.x + hPadding + tPadding
        val valueX = topLeft.x + width - hPadding - valueLayout.size.width - tPadding

        if (isLast) {
            // Draw background rectangle behind last row
            val bgHeight = lineHeight + lastRowBackgroundHeight
            val bgTextY = currentY + (lineHeight - bgHeight) / 2f
            if (!measureOnly && !hideSummaryTotalBg) {
                drawRect(
                    color = lastRowBackground,
                    topLeft = Offset(topLeft.x + hPadding, bgTextY),
                    size = Size(width - hPadding * 2, bgHeight)
                )
            }
        }
        if (!measureOnly) {
            // Draw label and value
            drawText(labelLayout, topLeft = Offset(labelX, labelY))
            drawText(valueLayout, topLeft = Offset(valueX, valueY))
        }
        // Draw row border (skip last and second last)
        if (!isLast && !isSecondLast) {
            val borderY =
                currentY + (lineHeight + rowSpacing) - ((lineHeight + rowSpacing - lineHeight) / 2f)

            if (!measureOnly && !hideBorder) {
                drawLine(
                    color = borderColor,
                    strokeWidth = stroke,
                    start = Offset(topLeft.x + hPadding + tPadding, borderY),
                    end = Offset(topLeft.x + width - hPadding - tPadding, borderY)
                )
            }
        }

        currentY += lineHeight + rowSpacing
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, currentY - topLeft.y)
    )
}


fun DrawScope.drawInvoiceSummaryRounded(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    width: Float,
    measureOnly: Boolean = false,
    horizontalPaddingDp: Float = 0f,
    textPaddingDp: Float = 50f,
    verticalPaddingDp: Float = 0f,
    rowSpacingDp: Float = 55f,
    borderStrokeDp: Float = 2f,
    lastRowBackgroundHeightDp: Float = 60f
): DrawBounds {
    val labelStyle = ctx.scaledStyle(
        state.style.summaryLabel.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )


    val valueStyle = ctx.scaledStyle(
        state.style.summaryValue.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )

    val labelStyleLast = ctx.scaledStyle(
        state.style.summaryLastRow.copy(
            fontFamily = state.fontFamily, color = state.color.neutral1
        )
    )

    val lastRowBackground = state.color.primary
    val borderColor = state.color.neutral3

    val tPadding = ctx.scaledDpSize(textPaddingDp)
    val hPadding = ctx.scaledDpSize(horizontalPaddingDp)
    val vPadding = ctx.scaledDpSize(verticalPaddingDp)
    val stroke = ctx.scaledDpSize(borderStrokeDp)
    val rowSpacing = ctx.scaledDpSize(rowSpacingDp)
    val lastRowBackgroundHeight = ctx.scaledDpSize(lastRowBackgroundHeightDp)
    val cornerRadius = ctx.scaledDpSize(35f)


    val rows = state.data.summary

    var currentY = topLeft.y + vPadding

    rows.forEachIndexed { index, (label, value) ->
        val isLast = index == rows.lastIndex
        val isSecondLast = index == rows.lastIndex - 1

        val labelTextStyle = if (isLast) labelStyleLast else labelStyle
        val valueTextStyle = if (isLast) labelStyleLast else valueStyle

        val labelLayout = ctx.textMeasurer.measure(label, labelTextStyle)
        val valueLayout = ctx.textMeasurer.measure(value, valueTextStyle)

        val lineHeight = maxOf(labelLayout.size.height, valueLayout.size.height)
        val labelY = currentY + (lineHeight - labelLayout.size.height) / 2f
        val valueY = currentY + (lineHeight - valueLayout.size.height) / 2f

        val labelX = topLeft.x + hPadding + tPadding
        val valueX = topLeft.x + width - hPadding - valueLayout.size.width - tPadding

        if (isLast) {
            // Draw background rectangle behind last row
            val bgHeight = lineHeight + lastRowBackgroundHeight
            val bgTextY = currentY + (lineHeight - bgHeight) / 2f
            if (!measureOnly) {

                drawRoundRect(
                    color = lastRowBackground,
                    topLeft = Offset(topLeft.x + hPadding, bgTextY),
                    size = Size(width - hPadding * 2, bgHeight),
                    cornerRadius = CornerRadius(cornerRadius)
                )

            }
        }
        if (!measureOnly) {
            // Draw label and value
            drawText(labelLayout, topLeft = Offset(labelX, labelY))
            drawText(valueLayout, topLeft = Offset(valueX, valueY))
        }
        // Draw row border (skip last and second last)
        if (!isLast && !isSecondLast) {
            val borderY =
                currentY + (lineHeight + rowSpacing) - ((lineHeight + rowSpacing - lineHeight) / 2f)

            if (!measureOnly) {
                drawLine(
                    color = borderColor,
                    strokeWidth = stroke,
                    start = Offset(topLeft.x + hPadding + tPadding, borderY),
                    end = Offset(topLeft.x + width - hPadding - tPadding, borderY)
                )
            }
        }

        currentY += lineHeight + rowSpacing
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, currentY - topLeft.y)
    )
}