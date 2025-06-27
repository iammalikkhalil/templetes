package org.apptrick.invotick.presentation.templetes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.layout.below
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.presentation.components.drawings.drawFooterSection
import org.apptrick.invotick.presentation.components.drawings.drawHeader
import org.apptrick.invotick.presentation.components.drawings.drawItemsTableRound
import org.apptrick.invotick.presentation.components.drawings.drawPartySection
import org.apptrick.invotick.presentation.components.drawings.drawPaymentRoundedSummarySection
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.invoiceTemplate3f(
    boxWidthPx: Float, boxHeightPx: Float, ctx: InvoiceRenderContext, state: InvoiceTemplateState
) {
    val paddingHorizontal = ctx.scaledDpSize(100f)
    val paddingVertical = ctx.scaledDpSize(100f)
    val width = boxWidthPx - paddingHorizontal * 2
    val height = boxHeightPx - paddingVertical * 2
    val below = 100f


    val topLeft = Offset(0f, 0f)

    val a = Offset(topLeft.x + boxWidthPx/2, topLeft.y)

    val b = Offset(topLeft.x + boxWidthPx, topLeft.y)

    val c= Offset(topLeft.x + boxWidthPx, topLeft.y + boxHeightPx)

    val path = Path().apply {
        moveTo(a.x, a.y)
        lineTo(b.x, b.y)
        lineTo(c.x, c.y)
        close()
    }

    drawPath(path = path, color = state.color.secondary.copy(alpha = 0.1f))

    val startingOffset = Offset(0f + paddingHorizontal, 0f + paddingVertical)


    val headerBounds = drawHeader(
        topLeft = startingOffset,
        width = width,
        paddingVerticalDp = 100f,
        ctx = ctx,
        state = state
    )

    val partySectionBounds = drawPartySection(
        topLeft = headerBounds.below(below, ctx), width = width, ctx = ctx, state = state,
    )

//    val tableBounds = drawItemsTable(
//        topLeft = partySectionBounds.below(below, ctx),
//        ctx = ctx,
//        state = state,
////        items = state.data.itemTable,
//        tableWidth = width
//    )


    //Rounded Table

    val tableBounds = drawItemsTableRound(
        topLeft = partySectionBounds.below(below, ctx),
        ctx = ctx,
        state = state,
        tableWidth = width, measureOnly = true
    )

    drawItemsTableRound(
        topLeft = partySectionBounds.below(below, ctx),
        ctx = ctx,
        state = state,
        tableWidth = width,
        tableHeightPx = tableBounds.height
    )

    drawPaymentRoundedSummarySection(
        topLeft = tableBounds.below(below, ctx),
        totalWidth = width,
        ctx = ctx,
        state = state,
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
    )
}