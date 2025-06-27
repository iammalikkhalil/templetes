package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.CornerRadius
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


fun DrawScope.drawItemsTableTooRound(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    tableWidth: Float,
    measureOnly: Boolean = false,
    rowHeightDp: Float = 120f,
    horizontalPaddingDp: Float = 0f,
    verticalPaddingDp: Float = 0f,
    rowSpacingDp: Float = 15f,
    headerHeightMultiplier: Float = 1.25f,
    textPaddingHorizontalDp: Float = 50f,
    cornerRadiusDp: Float = 100f,
    tableHeightPx: Float = 100f,
): DrawBounds {
    val headingStyle = ctx.scaledStyle(
        state.style.heading2.copy(
            fontFamily = state.fontFamily, color = state.color.neutral1
        )
    )
    val valueStyle = ctx.scaledStyle(
        state.style.detailText.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )

    val rowHeight = ctx.scaledDpSize(rowHeightDp)
    val vPadding = ctx.scaledDpSize(verticalPaddingDp)
    val hPadding = ctx.scaledDpSize(horizontalPaddingDp)
    val textPadding = ctx.scaledDpSize(textPaddingHorizontalDp)
    val rowSpacing = ctx.scaledDpSize(rowSpacingDp)
    val headerHeight = rowHeight * headerHeightMultiplier
    val cornerRadius = ctx.scaledDpSize(cornerRadiusDp)

    val items = state.data.itemTable

    val colWeights = listOf(2f, 1f, 1f, 1f, 1f, 1f)
    val totalWeight = colWeights.sum()
    val colWidths = colWeights.map { weight ->
        (weight / totalWeight) * (tableWidth - 2 * hPadding)
    }

    val headers = listOf("ITEM DESCRIPTION", "QTY", "PRICE", "DISCOUNT", "TAX", "AMOUNT")

    var currentY = topLeft.y + vPadding


    if (!measureOnly) {
        // --- Draw Header Background ---


        // --- Shadow ---
        drawShadow(
            topLeft = Offset(topLeft.x, topLeft.y + headerHeight),
            size = Size(tableWidth - 2 * hPadding, tableHeightPx - headerHeight),
            cornerRadius = cornerRadius / 1.5f
        )
        // --- Header Background ---
        drawRoundRect(
            color = state.color.primary,
            topLeft = Offset(topLeft.x + hPadding, currentY),
            size = Size(tableWidth - 2 * hPadding, headerHeight),
            cornerRadius = CornerRadius(cornerRadius),
        )

        // --- Body Background ---
        drawRoundRect(
            color = Color.White,
            topLeft = Offset(topLeft.x, topLeft.y + headerHeight),
            size = Size(tableWidth - 2 * hPadding, tableHeightPx - headerHeight),
            cornerRadius = CornerRadius(cornerRadius / 1.5f),
        )
    }

    // --- Draw Header Text ---
    var currentX = topLeft.x + hPadding
    headers.zip(colWidths).forEach { (header, colWidth) ->
        val layout = ctx.textMeasurer.measure(header, headingStyle)
        val centeredY = currentY + (headerHeight - layout.size.height) / 2f
        if (!measureOnly) {
            drawText(
                layout,
                topLeft = Offset(currentX + textPadding, centeredY),
            )
        }
        currentX += colWidth
    }

    currentY += headerHeight + ctx.scaledDpSize(50f)

    // --- Draw Item Rows with Optional Background + Rounded Corners ---
    items.forEach { item ->
        val values = listOf(
            item.description, item.qty.toString(), item.price, item.discount, item.tax, item.amount
        )

        currentX = topLeft.x + hPadding
        values.zip(colWidths).forEach { (text, colWidth) ->
            val layout = ctx.textMeasurer.measure(text, valueStyle)
            val centeredY = currentY + (rowHeight - layout.size.height) / 2f
            if (!measureOnly) {
                drawText(
                    layout, topLeft = Offset(currentX + textPadding, centeredY)
                )
            }
            currentX += colWidth
        }

        currentY += rowHeight + rowSpacing
    }

    val totalHeight = currentY - topLeft.y

    return DrawBounds(
        topLeft = topLeft, size = Size(tableWidth, totalHeight)
    )
}


fun DrawScope.drawItemsTableRound(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    tableWidth: Float,
    measureOnly: Boolean = false,
    rowHeightDp: Float = 120f,
    horizontalPaddingDp: Float = 0f,
    verticalPaddingDp: Float = 0f,
    rowSpacingDp: Float = 15f,
    headerHeightMultiplier: Float = 1.25f,
    textPaddingHorizontalDp: Float = 50f,
    cornerRadiusDp: Float = 50f,
    tableHeightPx: Float = 100f,
): DrawBounds {
    val headingStyle = ctx.scaledStyle(
        state.style.heading2.copy(
            fontFamily = state.fontFamily, color = state.color.neutral1
        )
    )
    val valueStyle = ctx.scaledStyle(
        state.style.detailText.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )

    val rowHeight = ctx.scaledDpSize(rowHeightDp)
    val vPadding = ctx.scaledDpSize(verticalPaddingDp)
    val hPadding = ctx.scaledDpSize(horizontalPaddingDp)
    val textPadding = ctx.scaledDpSize(textPaddingHorizontalDp)
    val rowSpacing = ctx.scaledDpSize(rowSpacingDp)
    val headerHeight = rowHeight * headerHeightMultiplier
    val cornerRadius = ctx.scaledDpSize(cornerRadiusDp)

    val items = state.data.itemTable

    val colWeights = listOf(2f, 1f, 1f, 1f, 1f, 1f)
    val totalWeight = colWeights.sum()
    val colWidths = colWeights.map { weight ->
        (weight / totalWeight) * (tableWidth - 2 * hPadding)
    }

    val headers = listOf("ITEM DESCRIPTION", "QTY", "PRICE", "DISCOUNT", "TAX", "AMOUNT")

    var currentY = topLeft.y + vPadding


    if (!measureOnly) {
        // --- Draw Header Background ---

        drawShadow(
            topLeft = Offset(topLeft.x + hPadding, currentY),
            size = Size(tableWidth - 2 * hPadding, tableHeightPx),
            cornerRadius = cornerRadius
        )

        val headerBounds = drawTopRoundedRect(
            color = state.color.primary,
            topLeft = Offset(topLeft.x + hPadding, currentY),
            size = Size(tableWidth - 2 * hPadding, headerHeight),
            cornerRadius = cornerRadius,
        )

        // --- Draw Body Background ---
        drawBottomRoundedRect(
            color = Color.White,
            topLeft = Offset(headerBounds.topLeft.x, headerBounds.topLeft.y + headerBounds.height),
            size = Size(tableWidth - 2 * hPadding, tableHeightPx - headerHeight),
            cornerRadius = cornerRadius,
        )
    }

    // --- Draw Header Text ---
    var currentX = topLeft.x + hPadding
    headers.zip(colWidths).forEach { (header, colWidth) ->
        val layout = ctx.textMeasurer.measure(header, headingStyle)
        val centeredY = currentY + (headerHeight - layout.size.height) / 2f
        if (!measureOnly) {
            drawText(
                layout,
                topLeft = Offset(currentX + textPadding, centeredY),
            )
        }
        currentX += colWidth
    }

    currentY += headerHeight + ctx.scaledDpSize(50f)

    // --- Draw Item Rows with Optional Background + Rounded Corners ---
    items.forEach { item ->
        val values = listOf(
            item.description, item.qty.toString(), item.price, item.discount, item.tax, item.amount
        )

        currentX = topLeft.x + hPadding
        values.zip(colWidths).forEach { (text, colWidth) ->
            val layout = ctx.textMeasurer.measure(text, valueStyle)
            val centeredY = currentY + (rowHeight - layout.size.height) / 2f
            if (!measureOnly) {
                drawText(
                    layout, topLeft = Offset(currentX + textPadding, centeredY)
                )
            }
            currentX += colWidth
        }

        currentY += rowHeight + rowSpacing
    }

    val totalHeight = currentY - topLeft.y

    return DrawBounds(
        topLeft = topLeft, size = Size(tableWidth, totalHeight)
    )
}


fun DrawScope.drawItemsTable(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    tableWidth: Float,
    measureOnly: Boolean = false,
    rowHeightDp: Float = 120f,
    horizontalPaddingDp: Float = 0f,
    verticalPaddingDp: Float = 0f,
    rowSpacingDp: Float = 15f,
    headerHeightMultiplier: Float = 1.25f,
    textPaddingHorizontalDp: Float = 50f,
    hideHeaderBg: Boolean = false,
    showOddRowsBg: Boolean = false,
): DrawBounds {
    val headingStyle = ctx.scaledStyle(
        state.style.heading2.copy(
            fontFamily = state.fontFamily,
            color = if (hideHeaderBg) state.color.neutral2 else state.color.neutral1
        )
    )

    val valueStyle = ctx.scaledStyle(
        state.style.detailText.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )

    val rowHeight = ctx.scaledDpSize(rowHeightDp)
    val vPadding = ctx.scaledDpSize(verticalPaddingDp)
    val hPadding = ctx.scaledDpSize(horizontalPaddingDp)
    val textPadding = ctx.scaledDpSize(textPaddingHorizontalDp)
    val rowSpacing = ctx.scaledDpSize(rowSpacingDp)
    val headerHeight = rowHeight * headerHeightMultiplier

    val items = state.data.itemTable

    val colWeights = listOf(2f, 1f, 1f, 1f, 1f, 1f)
    val totalWeight = colWeights.sum()
    val colWidths = colWeights.map { weight ->
        (weight / totalWeight) * (tableWidth - 2 * hPadding)
    }

    val headers = listOf("ITEM DESCRIPTION", "QTY", "PRICE", "DISCOUNT", "TAX", "AMOUNT")

    var currentY = topLeft.y + vPadding

    if (!measureOnly && !hideHeaderBg) {
        // --- Draw Header Background ---
        drawRect(
            color = state.color.primary,
            topLeft = Offset(topLeft.x + hPadding, currentY),
            size = Size(tableWidth - 2 * hPadding, headerHeight)
        )
    }

    // --- Draw Header Text ---
    var currentX = topLeft.x + hPadding
    headers.zip(colWidths).forEach { (header, colWidth) ->
        val layout = ctx.textMeasurer.measure(header, headingStyle)
        val centeredY = currentY + (headerHeight - layout.size.height) / 2f
        if (!measureOnly) {
            drawText(
                layout,
                topLeft = Offset(currentX + textPadding, centeredY),
            )
        }
        currentX += colWidth
    }

    currentY += headerHeight + ctx.scaledDpSize(30f)

    // --- Draw Item Rows ---
    items.forEachIndexed { index, item ->

        currentX = topLeft.x + hPadding

        if (index % 2 != 0 && showOddRowsBg && !measureOnly) {
            drawRect(
                color = state.color.secondary.copy(alpha = 0.2f),
                topLeft = Offset(x = topLeft.x + hPadding, y = currentY),
                size = Size(width = tableWidth - 2 * hPadding, height = rowHeight + ctx.scaledDpSize(5f))
            )
        }

        val values = listOf(
            item.description, item.qty.toString(), item.price, item.discount, item.tax, item.amount
        )

        values.zip(colWidths).forEach { (text, colWidth) ->
            val layout = ctx.textMeasurer.measure(text, valueStyle)
            val centeredY = currentY + (rowHeight + ctx.scaledDpSize(5f) - layout.size.height) / 2f
            if (!measureOnly) {
                drawText(
                    layout, topLeft = Offset(currentX + textPadding, centeredY)
                )
            }
            currentX += colWidth
        }


        currentY += rowHeight + rowSpacing
    }

    val totalHeight = currentY - topLeft.y

    return DrawBounds(
        topLeft = topLeft, size = Size(tableWidth, totalHeight)
    )
}
