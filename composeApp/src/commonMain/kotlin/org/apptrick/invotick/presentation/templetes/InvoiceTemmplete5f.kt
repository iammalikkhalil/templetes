package org.apptrick.invotick.presentation.templetes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.layout.below
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.presentation.components.drawings.drawFooterSection
import org.apptrick.invotick.presentation.components.drawings.drawHeaderLighter
import org.apptrick.invotick.presentation.components.drawings.drawImageScaled
import org.apptrick.invotick.presentation.components.drawings.drawItemsTable
import org.apptrick.invotick.presentation.components.drawings.drawPaymentSummarySection
import org.apptrick.invotick.presentation.components.drawings.drawToDetailPartySection
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.invoiceTemplate5f(
    boxWidthPx: Float, boxHeightPx: Float, ctx: InvoiceRenderContext, state: InvoiceTemplateState
) {
    val paddingHorizontal = ctx.scaledDpSize(100f)
    val paddingVertical = ctx.scaledDpSize(100f)
    val width = boxWidthPx - paddingHorizontal * 2
    val height = boxHeightPx - paddingVertical * 2
    val below = 100f
    val belowSmall = 100f

    val footerGap = ctx.scaledDpSize(60f)

    val startingOffset = Offset(0f + paddingHorizontal, 0f + paddingVertical)

    val headerBounds = drawHeaderLighter(
        topLeft = startingOffset,
        width = width,
        paddingVerticalDp = 60f,
        ctx = ctx,
        state = state,
        borderBottom = false,
        measureOnly = true,
    )

    //drawingBg
    val bgOffset = Offset(-1f, -1f)
    val bgSize =
        Size(boxWidthPx * 2 / 3, headerBounds.height + paddingVertical + ctx.scaledDpSize(10f))

    state.images.headerBackground?.let { image ->
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


    drawHeaderLighter(
        topLeft = startingOffset,
        width = width,
        paddingVerticalDp = 60f,
        ctx = ctx,
        state = state,
        borderBottom = false,
    )

    val partySectionBounds = drawToDetailPartySection(
        topLeft = headerBounds.below(belowSmall, ctx), width = width, ctx = ctx, state = state,
    )


    //  Rounded Table
    val tableBounds = drawItemsTable(
        topLeft = partySectionBounds.below(below, ctx),
        ctx = ctx,
        state = state,
        tableWidth = width,
        showOddRowsBg = true,
    )

    drawLine(
        color = state.color.neutral3,
        start = Offset(width - paddingHorizontal - ctx.scaledDpSize(450f), tableBounds.bottom + ctx.scaledDpSize(20f)),
        end = Offset(width - ctx.scaledDpSize(20f), tableBounds.bottom+ ctx.scaledDpSize(20f)),
        strokeWidth = ctx.scaledDpSize(2f)
    )

    drawPaymentSummarySection(
        topLeft = tableBounds.below(below, ctx),
        totalWidth = width,
        ctx = ctx,
        state = state,
        hideBorder = true,
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
    )
}