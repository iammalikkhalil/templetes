package org.apptrick.invotick.presentation.templetes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.layout.below
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.presentation.components.drawings.drawFooterSection
import org.apptrick.invotick.presentation.components.drawings.drawHeader
import org.apptrick.invotick.presentation.components.drawings.drawImageScaled
import org.apptrick.invotick.presentation.components.drawings.drawItemsTableTooRound
import org.apptrick.invotick.presentation.components.drawings.drawPartySection
import org.apptrick.invotick.presentation.components.drawings.drawPaymentSummarySection
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.invoiceTemplate1(
    boxWidthPx: Float, boxHeightPx: Float, ctx: InvoiceRenderContext, state: InvoiceTemplateState
) {
    val paddingHorizontal = ctx.scaledDpSize(100f)
    val paddingVertical = ctx.scaledDpSize(100f)
    val width = boxWidthPx - paddingHorizontal * 2
    val height = boxHeightPx - paddingVertical * 2
    val below = 100f


    //drawingBg

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


    val headerBounds = drawHeader(
        topLeft = startingOffset, width = width, paddingVerticalDp = 100f, ctx = ctx, state = state
    )

    val partySectionBounds = drawPartySection(
        topLeft = headerBounds.below(below, ctx), width = width, ctx = ctx, state = state,
    )

//        //Rounded Table
//
    val tableBounds = drawItemsTableTooRound(
        topLeft = partySectionBounds.below(below, ctx),
        ctx = ctx,
        state = state,
        tableWidth = width, measureOnly = true
    )

    drawItemsTableTooRound(
        topLeft = partySectionBounds.below(below, ctx),
        ctx = ctx,
        state = state,
        tableWidth = width,
        tableHeightPx = tableBounds.height
    )

    drawPaymentSummarySection(
        topLeft = tableBounds.below(below, ctx),
        totalWidth = width,
        ctx = ctx,
        state = state,
        hideSummaryTotalBg = true
    )

    val footerBounds = drawFooterSection(
        topLeft = Offset(0f, 0f), width = width, ctx = ctx, state = state, measureOnly = true
    )

    val footerOffset = Offset(startingOffset.x, height - footerBounds.height)

    drawFooterSection(
        topLeft = footerOffset,
        width = width,
        ctx = ctx,
        state = state,
        hideStamp = true,
    )
}