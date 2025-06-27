package org.apptrick.invotick.core.layout

import androidx.compose.ui.geometry.Offset
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.model.DrawBounds
import androidx.compose.ui.unit.dp
import org.apptrick.invotick.core.scaledDpSize

fun DrawBounds.below(
    gap: Float = 0f,
    ctx: InvoiceRenderContext
): Offset {
    val scaledGap = ctx.scaledDpSize(gap)
    return Offset(topLeft.x, bottom + scaledGap)
}

fun DrawBounds.above(
    height: Float,
    gap: Float = 0f,
    ctx: InvoiceRenderContext
): Offset {
    val scaledGap = ctx.density.run { gap.dp.toPx() } * ctx.scale
    val scaledHeight = ctx.density.run { height.dp.toPx() } * ctx.scale
    return Offset(topLeft.x, topLeft.y - scaledHeight - scaledGap)
}

fun DrawBounds.rightOf(
    gap: Float = 0f,
    ctx: InvoiceRenderContext
): Offset {
    val scaledGap = ctx.density.run { gap.dp.toPx() } * ctx.scale
    return Offset(right + scaledGap, topLeft.y)
}

fun DrawBounds.leftOf(
    width: Float,
    gap: Float = 0f,
    ctx: InvoiceRenderContext
): Offset {
    val scaledGap = ctx.density.run { gap.dp.toPx() } * ctx.scale
    val scaledWidth = ctx.density.run { width.dp.toPx() } * ctx.scale
    return Offset(topLeft.x - scaledWidth - scaledGap, topLeft.y)
}

fun DrawBounds.alignRight(
    width: Float,
    ctx: InvoiceRenderContext
): Float {
    val scaledWidth = ctx.density.run { width.dp.toPx() } * ctx.scale
    return right - scaledWidth
}

fun DrawBounds.alignCenterX(
    width: Float,
    ctx: InvoiceRenderContext
): Float {
    val scaledWidth = ctx.density.run { width.dp.toPx() } * ctx.scale
    return topLeft.x + (size.width - scaledWidth) / 2
}

fun DrawBounds.alignCenterY(
    height: Float,
    ctx: InvoiceRenderContext
): Float {
    val scaledHeight = ctx.density.run { height.dp.toPx() } * ctx.scale
    return topLeft.y + (size.height - scaledHeight) / 2
}