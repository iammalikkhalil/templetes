package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.drawStyledText
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.scaledStyle
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

fun DrawScope.fromSection(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    lines: List<String> = listOf(
        "From",
        "Business Name",
    ),
    measureOnly: Boolean = false,
): DrawBounds {
    var currentY = topLeft.y
    var maxWidth = 0f

    // Pre-calculate fixed line height for both heading and value styles
    val headingStyle = ctx.scaledStyle(state.style.fromHeading3.copy(fontFamily = state.fontFamily, color = state.color.neutral2))
    val valueStyle = ctx.scaledStyle(
        state.style.fromValue.copy(
            fontFamily = state.fontFamily,
            color = state.color.neutral2,
        )
    )


    val headingLineHeight = ctx.textMeasurer.measure("Hg", headingStyle).size.height.toFloat()
    val valueLineHeight = ctx.textMeasurer.measure("Hg", valueStyle).size.height.toFloat()

    lines.forEachIndexed { index, text ->

        val isHeading = index == 0
        val style = if (isHeading) headingStyle else valueStyle
        val lineHeight = if (isHeading) headingLineHeight else valueLineHeight

        val layout = ctx.textMeasurer.measure(text, style)
        maxWidth = maxOf(maxWidth, layout.size.width.toFloat())


        if (!measureOnly) {
            drawStyledText(
                text = text, topLeft = Offset(topLeft.x, currentY), originalStyle = style, ctx = ctx
            )
        }

        currentY += lineHeight
    }

    val totalHeight = currentY - topLeft.y
    return DrawBounds(
        topLeft = topLeft, size = Size(maxWidth, totalHeight)
    )
}



fun DrawScope.fromSectionLight(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    lines: List<String> = listOf(
        "From",
        "Business Name",
    ),
    measureOnly: Boolean = false,
): DrawBounds {
    var currentY = topLeft.y
    var maxWidth = 0f

    // Pre-calculate fixed line height for both heading and value styles
    val headingStyle = ctx.scaledStyle(state.style.fromHeading3.copy(fontFamily = state.fontFamily, color = state.color.neutral1))
    val valueStyle = ctx.scaledStyle(
        state.style.fromValue.copy(
            fontFamily = state.fontFamily,
            color = state.color.neutral1,
        )
    )


    val headingLineHeight = ctx.textMeasurer.measure("Hg", headingStyle).size.height.toFloat()
    val valueLineHeight = ctx.textMeasurer.measure("Hg", valueStyle).size.height.toFloat()

    lines.forEachIndexed { index, text ->

        val isHeading = index == 0
        val style = if (isHeading) headingStyle else valueStyle
        val lineHeight = if (isHeading) headingLineHeight else valueLineHeight

        val layout = ctx.textMeasurer.measure(text, style)
        maxWidth = maxOf(maxWidth, layout.size.width.toFloat())


        if (!measureOnly) {
            drawStyledText(
                text = text, topLeft = Offset(topLeft.x, currentY), originalStyle = style, ctx = ctx
            )
        }

        currentY += lineHeight
    }

    val totalHeight = currentY - topLeft.y
    return DrawBounds(
        topLeft = topLeft, size = Size(maxWidth, totalHeight)
    )
}