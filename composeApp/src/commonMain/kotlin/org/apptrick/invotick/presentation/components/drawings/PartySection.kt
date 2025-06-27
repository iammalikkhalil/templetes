package org.apptrick.invotick.presentation.components.drawings


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.drawToFromPartySection(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    width: Float,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)


    // --- Measure both sections ---
    val fromBounds = fromSection(
        topLeft = Offset(0f, 0f),
        ctx = ctx,
        lines = state.data.business,
        state = state,
        measureOnly = true
    )

    val toSectionBounds = fromSection(
        Offset(0f, 0f), ctx, lines = state.data.client, state = state, measureOnly = true
    )

    val contentHeight = maxOf(fromBounds.height, toSectionBounds.height)
    val totalHeight = contentHeight + 2 * paddingVertical

    val baseY = topLeft.y + paddingVertical


    val leftOffset = Offset(
        x = topLeft.x + paddingHorizontal / 2, y = baseY
    )

    val rightOffset = Offset(
        x = topLeft.x + width - toSectionBounds.width - paddingHorizontal / 2, y = baseY
    )

    // --- Draw phase ---
    if (!measureOnly) {
        fromSection(leftOffset, ctx, state = state, lines = state.data.business)
        fromSection(rightOffset, ctx, state = state, lines = state.data.client)
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeight)
    )
}

fun DrawScope.drawToDetailPartySection(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    width: Float,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)


    // --- Measure both sections ---
    val invoiceDetailBounds = drawInvoiceDetail(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, measureOnly = true
    )

    val toSectionBounds = fromSection(
        Offset(0f, 0f), ctx, lines = state.data.client, state = state, measureOnly = true
    )

    val contentHeight = maxOf(invoiceDetailBounds.height, toSectionBounds.height)
    val totalHeight = contentHeight + paddingVertical


    val leftOffset = Offset(
        x = topLeft.x + paddingHorizontal / 2,
        y = topLeft.y + (totalHeight - toSectionBounds.height) / 2f
    )

    val rightOffset = Offset(
        x = topLeft.x + width - invoiceDetailBounds.width - paddingHorizontal / 2,
        y = topLeft.y + (totalHeight - invoiceDetailBounds.height) / 2f
    )

    // --- Draw phase ---
    if (!measureOnly) {
        fromSection( leftOffset, ctx, state = state, lines = state.data.client)
        drawInvoiceDetail(rightOffset, ctx, state = state, measureOnly = false)
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeight)
    )
}


fun DrawScope.drawPartySection(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    width: Float,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)


    // --- Measure both sections ---
    val invoiceDetailBounds = drawInvoiceDetail(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, measureOnly = true
    )

    val toSectionBounds = fromSection(
        Offset(0f, 0f), ctx, lines = state.data.client, state = state, measureOnly = true
    )

    val contentHeight = maxOf(invoiceDetailBounds.height, toSectionBounds.height)
    val totalHeight = contentHeight + paddingVertical


    val leftOffset = Offset(
        x = topLeft.x + paddingHorizontal / 2,
        y = topLeft.y + (totalHeight - invoiceDetailBounds.height) / 2f
    )

    val rightOffset = Offset(
        x = topLeft.x + width - toSectionBounds.width - paddingHorizontal / 2,
        y = topLeft.y + (totalHeight - invoiceDetailBounds.height) / 2f
    )

    // --- Draw phase ---
    if (!measureOnly) {
        drawInvoiceDetail(leftOffset, ctx, state = state, measureOnly = false)
        fromSection(rightOffset, ctx, state = state, lines = state.data.client)
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeight)
    )
}




fun DrawScope.drawPaymentSummarySection(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    totalWidth: Float,
    summaryWidthDp: Float = 800f,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false,
    hideSummaryTotalBg: Boolean = false,
    hideBorder: Boolean = false,
    paymentCenter: Boolean = false,
): DrawBounds {

    val hPadding = ctx.scaledDpSize(paddingHorizontalDp)
    val vPadding = ctx.scaledDpSize(paddingVerticalDp)
    val summaryWidth = ctx.scaledDpSize(summaryWidthDp)
    val scaledTotalWidth = ctx.scaledDpSize(totalWidth)

    // --- Measure both sections ---
    val paymentBounds = fromSection(
        topLeft = Offset(0f, 0f),
        ctx = ctx,
        state = state,
        state.data.paymentMethod,
        measureOnly = true
    )

    val summaryBounds = drawInvoiceSummary(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, width = summaryWidth, measureOnly = true
    )

    val contentHeight = maxOf(paymentBounds.height, summaryBounds.height)
    val totalHeight = contentHeight + 2 * vPadding

    val leftOffset = Offset(
        x = topLeft.x + hPadding, y = topLeft.y + if (paymentCenter) (totalHeight - paymentBounds.height)/2f else vPadding
    )

    val rightOffset = Offset(
        x = topLeft.x + (totalWidth - hPadding - summaryBounds.width), y = topLeft.y + if (paymentCenter) (totalHeight - summaryBounds.height)/2f else vPadding
    )

    if (!measureOnly) {
        fromSection(leftOffset, ctx, state = state, state.data.paymentMethod)

        drawInvoiceSummary(
            topLeft = rightOffset,
            ctx = ctx,
            state = state,
            width = summaryWidth,
            measureOnly = false,
            hideSummaryTotalBg = hideSummaryTotalBg,
            hideBorder = hideBorder
        )
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(scaledTotalWidth, totalHeight)
    )
}


fun DrawScope.drawPaymentRoundedSummarySection(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    totalWidth: Float,
    summaryWidthDp: Float = 800f,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false
): DrawBounds {

    val hPadding = ctx.scaledDpSize(paddingHorizontalDp)
    val vPadding = ctx.scaledDpSize(paddingVerticalDp)
    val summaryWidth = ctx.scaledDpSize(summaryWidthDp)
    val scaledTotalWidth = ctx.scaledDpSize(totalWidth)

    // --- Measure both sections ---
    val paymentBounds = fromSection(
        topLeft = Offset(0f, 0f),
        ctx = ctx,
        state = state,
        state.data.paymentMethod,
        measureOnly = true
    )

    val summaryBounds = drawInvoiceSummaryRounded(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, width = summaryWidth, measureOnly = true
    )

    val contentHeight = maxOf(paymentBounds.height, summaryBounds.height)
    val totalHeight = contentHeight + 2 * vPadding

    val leftOffset = Offset(
        x = topLeft.x + hPadding, y = topLeft.y + vPadding
    )

    val rightOffset = Offset(
        x = topLeft.x + (totalWidth - hPadding - summaryBounds.width), y = topLeft.y + vPadding
    )

    if (!measureOnly) {
        fromSection(leftOffset, ctx, state = state, state.data.paymentMethod)

        drawInvoiceSummaryRounded(
            topLeft = rightOffset,
            ctx = ctx,
            state = state,
            width = summaryWidth,
            measureOnly = false
        )

    }

    return DrawBounds(
        topLeft = topLeft, size = Size(scaledTotalWidth, totalHeight)
    )
}
