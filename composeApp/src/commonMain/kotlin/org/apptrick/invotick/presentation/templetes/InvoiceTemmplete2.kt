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
import org.apptrick.invotick.presentation.components.drawings.drawItemsTableRound
import org.apptrick.invotick.presentation.components.drawings.drawLogoFromDarkHeader
import org.apptrick.invotick.presentation.components.drawings.drawPartySection
import org.apptrick.invotick.presentation.components.drawings.drawPaymentRoundedSummarySection
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.invoiceTemplate2(
    boxWidthPx: Float, boxHeightPx: Float, ctx: InvoiceRenderContext, state: InvoiceTemplateState
) {
    val paddingHorizontal = ctx.scaledDpSize(100f)
    val paddingVertical = ctx.scaledDpSize(100f)
    val width = boxWidthPx - paddingHorizontal * 2
    val height = boxHeightPx - paddingVertical * 2
    val below = 100f

    val startingOffset = Offset(0f + paddingHorizontal, 0f + paddingVertical)


    val headerBounds = drawHeader(
        topLeft = startingOffset,
        width = width,
        paddingVerticalDp = 100f,
        ctx = ctx,
        state = state,
        measureOnly = true
    )

    state.images.headerBackground?.let { image ->
        drawImageScaled(image = image,
            topLeft = Offset(0f, 0f),
            size = Size(boxWidthPx, headerBounds.height + paddingVertical))
    } ?: run {
        drawRect(
            color = Color.LightGray,
            topLeft = Offset.Zero,
            size = Size(boxWidthPx, headerBounds.height + paddingVertical)
        )
    }


    drawLogoFromDarkHeader(
        topLeft = startingOffset,
        width = width,
        paddingVerticalDp = 100f,
        ctx = ctx,
        state = state,
        borderBottom = false,
    )


    val partySectionBounds = drawPartySection(
        topLeft = headerBounds.below(below, ctx), width = width, ctx = ctx, state = state,
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

    val footerOffset = Offset(startingOffset.x, height - footerBounds.height)

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
            color = Color.LightGray,
            topLeft = footerBgOffset,
            size = Size(boxWidthPx, ctx.scaledDpSize(80f))
        )
    }
}
