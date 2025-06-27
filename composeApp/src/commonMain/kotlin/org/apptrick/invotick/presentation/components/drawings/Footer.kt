package org.apptrick.invotick.presentation.components.drawings


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.drawFooterSection(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    width: Float,
    paddingTopDp: Float = 0f,
    paddingBottomDp: Float = 0f,
    paddingRightDp: Float = 0f,
    paddingLeftDp: Float = 0f,
    leftItemsGapDp: Float = 30f,
    measureOnly: Boolean = false,
    hideStamp: Boolean = false,
): DrawBounds {

    val paddingTop = ctx.scaledDpSize(paddingTopDp)
    val paddingBottom = ctx.scaledDpSize(paddingBottomDp)
    val leftItemsGap = ctx.scaledDpSize(leftItemsGapDp)
    val paddingLeft = ctx.scaledDpSize(paddingLeftDp)


    val contentStartX = topLeft.x + paddingLeft

    // --- Measure components ---
    val termsBounds = fromSection(
        topLeft = Offset(0f, 0f), ctx = ctx, lines = state.data.termsAndConditions
        , state = state, measureOnly = true
    )

    val stampBounds = drawLogo(
        topLeft = Offset(0f, 0f),
        ctx = ctx,
        state = state,
        sizeDp = 360f,
        text = "STAMP",
        measureOnly = true,
    )

    val signatureBounds = drawLogo(
        topLeft = Offset(0f, 0f),
        ctx = ctx,
        state = state,
        sizeDp = 440f,
        text = "SIGNATURE",
        measureOnly = true
    )

    // Total left content height (terms + stamp)
    val leftContentHeight = termsBounds.height + stampBounds.height + leftItemsGap
    val contentHeight = maxOf(leftContentHeight, signatureBounds.height)
    val totalHeight = paddingTop + contentHeight + paddingBottom

    // --- Left Side Offsets ---
    val stampOffset = Offset(
        x = contentStartX + (termsBounds.width / 2) - stampBounds.width / 2,
        y = topLeft.y + paddingTop
    )

    val termsOffset = Offset(
        x = contentStartX, y = topLeft.y + paddingTop + stampBounds.height + leftItemsGap
    )

    // --- Right Side Offset (vertically centered) ---
    val signatureOffset = Offset(
        x = topLeft.x * 2 + (width - signatureBounds.width - stampOffset.x),
        y = topLeft.y + (totalHeight - signatureBounds.height) / 2
    )

    if (!measureOnly) {
        fromSection(
            topLeft = termsOffset,
            ctx = ctx,
            state = state,
            lines = state.data.termsAndConditions,
        )

        drawLogo(
            topLeft = stampOffset,
            ctx = ctx,
            state = state,
            sizeDp = 360f,
            text = "STAMP",
            measureOnly = hideStamp,
        )

        drawLogo(
            topLeft = signatureOffset,
            ctx = ctx,
            state = state,
            sizeDp = 440f,
            text = "SIGNATURE",
        )

    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeight)
    )
}