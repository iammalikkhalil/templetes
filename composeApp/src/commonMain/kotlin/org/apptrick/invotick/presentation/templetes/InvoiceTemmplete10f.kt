package org.apptrick.invotick.presentation.templetes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.layout.below
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.presentation.components.drawings.drawFooterSection
import org.apptrick.invotick.presentation.components.drawings.drawImageScaled
import org.apptrick.invotick.presentation.components.drawings.drawItemsTableRound
import org.apptrick.invotick.presentation.components.drawings.drawPartySection
import org.apptrick.invotick.presentation.components.drawings.drawPaymentRoundedSummarySection
import org.apptrick.invotick.presentation.components.drawings.drawStyledHeader
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.invoiceTemplate10f(
    boxWidthPx: Float, boxHeightPx: Float, ctx: InvoiceRenderContext, state: InvoiceTemplateState
) {
    val paddingHorizontal = ctx.scaledDpSize(100f)
    val paddingVertical = ctx.scaledDpSize(100f)
    val width = boxWidthPx - paddingHorizontal * 2
    val height = boxHeightPx - paddingVertical * 2
    val below = 100f
    val belowSmall = 60f

    val startingOffset = Offset(0f + paddingHorizontal, 0f)


    val headerBounds = drawStyledHeader(
        topLeft = startingOffset,
        width = width,
        paddingVerticalDp = 60f,
        ctx = ctx,
        state = state,
    )


    val partySectionBounds = drawPartySection(
        topLeft = headerBounds.below(belowSmall, ctx), width = width, ctx = ctx, state = state,
    )

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

    val footerOffset = Offset(startingOffset.x, height - footerBounds.height + ctx.scaledDpSize(belowSmall))

    drawFooterSection(
        topLeft = footerOffset,
        width = width,
        ctx = ctx,
        state = state,
    )

    val footerBgOffset = Offset(0f, boxHeightPx - ctx.scaledDpSize(80f))

    state.images.footerBackground?.let { image ->
        drawImageScaled(image = image,
            topLeft = footerBgOffset,
            size = Size(boxWidthPx, ctx.scaledDpSize(80f)))
    } ?: run {
        drawRect(
            color = state.color.primary,
            topLeft = footerBgOffset,
            size = Size(boxWidthPx, ctx.scaledDpSize(80f))
        )
    }
}