package org.apptrick.invotick.presentation.templetes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.layout.below
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.presentation.components.drawings.drawFooterSection
import org.apptrick.invotick.presentation.components.drawings.drawHeaderDetail
import org.apptrick.invotick.presentation.components.drawings.drawImageScaled
import org.apptrick.invotick.presentation.components.drawings.drawItemsTable
import org.apptrick.invotick.presentation.components.drawings.drawPaymentSummarySection
import org.apptrick.invotick.presentation.components.drawings.drawToFromPartySection
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.invoiceTemplate4f(
    boxWidthPx: Float, boxHeightPx: Float, ctx: InvoiceRenderContext, state: InvoiceTemplateState
) {
    val paddingHorizontal = ctx.scaledDpSize(100f)
    val paddingVertical = ctx.scaledDpSize(60f)
    val width = boxWidthPx - paddingHorizontal * 2
    val height = boxHeightPx - paddingVertical * 2
    val below = 100f
    val belowSmall = 100f

    val footerGap = ctx.scaledDpSize(100f)


    val bgOffset = Offset(0f, 0f)
    val bgSize = Size(boxWidthPx, boxHeightPx)


    state.images.background?.let { image ->
        drawImageScaled(
            image = image,
            topLeft = bgOffset,
            size = bgSize,
        )
    } ?: run {
        drawRect(
            color = Color.LightGray,
            topLeft = bgOffset,
            size = bgSize,
        )
    }

    val startingOffset = Offset(0f + paddingHorizontal, 0f + paddingVertical)


    val headerBounds = drawHeaderDetail(
        topLeft = startingOffset, width = width, paddingVerticalDp = 60f, ctx = ctx, state = state
    )

    val partySectionBounds = drawToFromPartySection(
        topLeft = headerBounds.below(belowSmall, ctx), width = width, ctx = ctx, state = state,
    )

//        //Rounded Table
//
    val tableBounds = drawItemsTable(
        topLeft = partySectionBounds.below(below, ctx),
        ctx = ctx,
        state = state,
        tableWidth = width,
        hideHeaderBg = true,
        rowHeightDp = 100f,
    )

    drawLine(
        color = state.color.neutral3,
        start = Offset(width - paddingHorizontal - ctx.scaledDpSize(550f), tableBounds.bottom),
        end = Offset(width - ctx.scaledDpSize(50f), tableBounds.bottom),
        strokeWidth = ctx.scaledDpSize(2f)
    )

    drawPaymentSummarySection(
        topLeft = tableBounds.below(below, ctx),
        totalWidth = width,
        ctx = ctx,
        state = state,
        hideSummaryTotalBg = true,
        hideBorder = true,
        paymentCenter = true
    )

    val footerBounds = drawFooterSection(
        topLeft = Offset(0f, 0f), width = width, ctx = ctx, state = state, measureOnly = true
    )

    val footerOffset = Offset(startingOffset.x, height - footerGap - footerBounds.height)

    drawFooterSection(
        topLeft = footerOffset,
        width = width,
        ctx = ctx,
        state = state,
        hideStamp = true,
    )
}