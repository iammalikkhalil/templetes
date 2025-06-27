package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.drawStyledHeading
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.core.scaledStyle
import org.apptrick.invotick.presentation.state.InvoiceTemplateState


fun DrawScope.drawHeaderDetailLogoLight(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = false,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false,
    rowGapPx: Float = 10f
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)
    val rowGap = ctx.scaledDpSize(rowGapPx)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(currentX, 0f), ctx = ctx, state = state, measureOnly = true
    )

    // --- Measure both sections ---
    val invoiceDetailBounds = drawInvoiceDetail(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, measureOnly = true
    )

    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = Color.White)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )

    // --- Calculate layout ---
    val rightHeight = logoBounds.height + headingBounds.height + rowGap
    val maxContentHeight = maxOf(rightHeight, invoiceDetailBounds.height)


    val totalHeaderHeight = maxContentHeight + paddingVertical
    var currentY = topLeft.y
    val rightX = topLeft.x + width

    // invoice detail Offset
    val invoiceDetailOffset = Offset(
        currentX, currentY + (maxContentHeight - invoiceDetailBounds.height) / 2f
    )

    currentY = topLeft.y + (totalHeaderHeight - rightHeight) / 2f

    //  Right-align Offset
    val logoOffset = Offset(
        x = rightX - headingBounds.width + (headingBounds.width - logoBounds.width) / 2,
        y = currentY
    )

    // heading Offsets
    val headingOffset = Offset(
        x = rightX - headingBounds.width, y = currentY + logoBounds.height + rowGap
    )


    // --- Final Draw Phase ---
    if (!measureOnly) {
        drawLogo(logoOffset, ctx, state, white = true)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffset,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
        )

        drawInvoiceDetail(
            topLeft = invoiceDetailOffset, ctx = ctx, state = state, lightMode = true
        )


        if (borderBottom) {
            // ✅ Bottom border line
            val borderY = topLeft.y + totalHeaderHeight
            drawLine(
                color = state.color.neutral3,
                start = Offset(topLeft.x + paddingHorizontal, borderY),
                end = Offset(topLeft.x + width - paddingHorizontal, borderY),
                strokeWidth = ctx.scaledDpSize(2f)
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeaderHeight)
    )
}


fun DrawScope.drawHeaderDetail(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = false,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false,
    rowGapPx: Float = 40f
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)
    val rowGap = ctx.scaledDpSize(rowGapPx)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(currentX, 0f), ctx = ctx, state = state, measureOnly = true
    )

    // --- Measure both sections ---
    val invoiceDetailBounds = drawInvoiceDetail(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, measureOnly = true
    )

    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = state.color.primary)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )

    // --- Calculate layout ---
    val leftHeight = logoBounds.height + headingBounds.height + rowGap
    val maxContentHeight = maxOf(leftHeight, invoiceDetailBounds.height)


    val totalHeaderHeight = maxContentHeight + paddingVertical
    val currentY = topLeft.y

    // logo Offsets
    val logoOffset = Offset(
        currentX + (headingBounds.width - logoBounds.width) / 2f,
        currentY + (maxContentHeight - leftHeight) / 2f
    )

    // heading Offsets
    val headingOffset = Offset(currentX, currentY + logoBounds.height + rowGap)


    //  Right-align Offset
    val invoiceDetailOffset = Offset(
        (width - paddingHorizontal - invoiceDetailBounds.width),
        currentY + (maxContentHeight - invoiceDetailBounds.height) / 2f
    )


//    val headingX = topLeft.x + width - paddingHorizontal - headingBounds.width // - 5f * scale
//    val headingY = baseY + (maxContentHeight - headingBounds.height) / 2f
//    val headingOffsetCentered = Offset(headingX, headingY)


    // --- Final Draw Phase ---
    if (!measureOnly) {
        drawLogo(logoOffset, ctx, state)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffset,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
        )

        drawInvoiceDetail(
            topLeft = invoiceDetailOffset, ctx = ctx, state = state,
        )


        if (borderBottom) {
            // ✅ Bottom border line
            val borderY = topLeft.y + totalHeaderHeight
            drawLine(
                color = state.color.neutral3,
                start = Offset(topLeft.x + paddingHorizontal, borderY),
                end = Offset(topLeft.x + width - paddingHorizontal, borderY),
                strokeWidth = ctx.scaledDpSize(2f)
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeaderHeight)
    )
}

fun DrawScope.drawLogoFromDarkHeader(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = true,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, measureOnly = true
    )

    // 2. Measure FromSection
    val fromBounds = fromSectionLight(
        topLeft = Offset(0f, 0f),
        ctx = ctx,
        lines = state.data.business,
        state = state,
        measureOnly = true
    )

    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = state.color.neutral1)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )

    // --- Calculate layout ---
    val maxContentHeight = maxOf(logoBounds.height, fromBounds.height, headingBounds.height)


    val totalHeaderHeight = maxContentHeight + paddingVertical
    val baseY = topLeft.y

    // Center Y for each component
    val fromOffset = Offset(currentX, baseY + (maxContentHeight - fromBounds.height) / 2f)

    currentX += width + paddingHorizontal - logoBounds.width

    val logoOffset = Offset(currentX, baseY + (maxContentHeight - logoBounds.height) / 2f)

    currentX = currentX - ctx.scaledDpSize(50f) - headingBounds.width

    //  Right-align the heading
    val headingOffset = Offset(currentX, baseY + (maxContentHeight - headingBounds.height) / 2f)

    // --- Final Draw Phase ---
    if (!measureOnly) {
        drawLogoLight(logoOffset, ctx, state, measureOnly = false)
        fromSectionLight(fromOffset, ctx, state, state.data.business, measureOnly = false)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffset,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
            measureOnly = false
        )


        if (borderBottom) {
            // ✅ Bottom border line
            val borderY = topLeft.y + totalHeaderHeight
            drawLine(
                color = state.color.neutral3,
                start = Offset(topLeft.x + paddingHorizontal, borderY),
                end = Offset(topLeft.x + width - paddingHorizontal, borderY),
                strokeWidth = ctx.scaledDpSize(2f)
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeaderHeight)
    )
}


fun DrawScope.drawLogoFromTitleDarkHeader(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = true,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(currentX, 0f), ctx = ctx, state = state, measureOnly = true
    )
    currentX += logoBounds.width + ctx.scaledDpSize(60f)

    // 2. Measure FromSection
    val fromBounds = fromSectionLight(
        topLeft = Offset(currentX, 0f),
        ctx = ctx,
        lines = state.data.business,
        state = state,
        measureOnly = true
    )

    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = state.color.neutral1)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )

    // --- Calculate layout ---
    val maxContentHeight = maxOf(logoBounds.height, fromBounds.height, headingBounds.height)


    val totalHeaderHeight = maxContentHeight + paddingVertical
    val baseY = topLeft.y

    // Center Y for each component
    val logoOffset =
        Offset(logoBounds.topLeft.x, baseY + (maxContentHeight - logoBounds.height) / 2f)
    val fromOffset =
        Offset(fromBounds.topLeft.x, baseY + (maxContentHeight - fromBounds.height) / 2f)

    //  Right-align the heading
    val headingX = topLeft.x + width - paddingHorizontal - headingBounds.width // - 5f * scale
    val headingY = baseY + (maxContentHeight - headingBounds.height) / 2f
    val headingOffsetCentered = Offset(headingX, headingY)

    // --- Final Draw Phase ---
    if (!measureOnly) {
        drawLogoLight(logoOffset, ctx, state, measureOnly = false)
        fromSectionLight(fromOffset, ctx, state, state.data.business, measureOnly = false)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffsetCentered,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
            measureOnly = false
        )


        if (borderBottom) {
            // ✅ Bottom border line
            val borderY = topLeft.y + totalHeaderHeight
            drawLine(
                color = state.color.neutral3,
                start = Offset(topLeft.x + paddingHorizontal, borderY),
                end = Offset(topLeft.x + width - paddingHorizontal, borderY),
                strokeWidth = ctx.scaledDpSize(2f)
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeaderHeight)
    )
}


fun DrawScope.drawHeaderLighter(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = true,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false,
    headingWhite: Boolean = false,
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(currentX, 0f), ctx = ctx, state = state, measureOnly = true
    )
    currentX += logoBounds.width + ctx.scaledDpSize(60f)

    // 2. Measure FromSection
    val fromBounds = fromSectionLight(
        topLeft = Offset(currentX, 0f),
        ctx = ctx,
        lines = state.data.business,
        state = state,
        measureOnly = true
    )

    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = if (headingWhite) Color.White else state.color.primary)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )

    // --- Calculate layout ---
    val maxContentHeight = maxOf(logoBounds.height, fromBounds.height, headingBounds.height)


    val totalHeaderHeight = maxContentHeight + paddingVertical
    val baseY = topLeft.y

    // Center Y for each component
    val logoOffset =
        Offset(logoBounds.topLeft.x, baseY + (maxContentHeight - logoBounds.height) / 2f)
    val fromOffset =
        Offset(fromBounds.topLeft.x, baseY + (maxContentHeight - fromBounds.height) / 2f)

    //  Right-align the heading
    val headingX = topLeft.x + width - paddingHorizontal - headingBounds.width // - 5f * scale
    val headingY = baseY + (maxContentHeight - headingBounds.height) / 2f
    val headingOffsetCentered = Offset(headingX, headingY)

    // --- Final Draw Phase ---
    if (!measureOnly) {
        drawLogoLight(logoOffset, ctx, state, measureOnly = false)
        fromSectionLight(fromOffset, ctx, state, state.data.business, measureOnly = false)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffsetCentered,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
            measureOnly = false
        )


        if (borderBottom) {
            // ✅ Bottom border line
            val borderY = topLeft.y + totalHeaderHeight
            drawLine(
                color = state.color.neutral3,
                start = Offset(topLeft.x + paddingHorizontal, borderY),
                end = Offset(topLeft.x + width - paddingHorizontal, borderY),
                strokeWidth = ctx.scaledDpSize(2f)
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeaderHeight)
    )
}


fun DrawScope.drawHeaderFromTitleLogo(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = true,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false,
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(0f, 0f), ctx = ctx, state = state, measureOnly = true
    )

    // 2. Measure FromSection
    val fromBounds = fromSection(
        topLeft = Offset(0f, 0f),
        ctx = ctx,
        lines = state.data.business,
        state = state,
        measureOnly = true
    )

    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = state.color.primary)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )

    // --- Calculate layout ---
    val maxContentHeight = maxOf(logoBounds.height, fromBounds.height, headingBounds.height)


    val totalHeaderHeight = maxContentHeight + paddingVertical
    val baseY = topLeft.y

    // Center Y for each component
    val fromOffset = Offset(currentX, baseY + (maxContentHeight - logoBounds.height) / 2f)
    currentX += fromBounds.width + ctx.scaledDpSize(60f)

//    //  Right-align the heading
//    val headingX = topLeft.x + width - paddingHorizontal - headingBounds.width
//    val headingY =
    val headingOffsetCentered =
        Offset(currentX, baseY + (maxContentHeight - headingBounds.height) / 2f)


    val logoOffset = Offset(
        topLeft.x + width - logoBounds.width - paddingHorizontal,
        baseY + (maxContentHeight - logoBounds.height) / 2f
    )


    // --- Final Draw Phase ---
    if (!measureOnly) {
        drawLogo(logoOffset, ctx, state, measureOnly = false)
        fromSection(fromOffset, ctx, state, state.data.business, measureOnly = false)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffsetCentered,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
            measureOnly = false
        )


        if (borderBottom) {
            // ✅ Bottom border line
            val borderY = topLeft.y + totalHeaderHeight
            drawLine(
                color = state.color.neutral3,
                start = Offset(topLeft.x + paddingHorizontal, borderY),
                end = Offset(topLeft.x + width - paddingHorizontal, borderY),
                strokeWidth = ctx.scaledDpSize(2f)
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeaderHeight)
    )
}


fun DrawScope.drawHeader(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = true,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false,
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(currentX, 0f), ctx = ctx, state = state, measureOnly = true
    )
    currentX += logoBounds.width + ctx.scaledDpSize(60f)

    // 2. Measure FromSection
    val fromBounds = fromSection(
        topLeft = Offset(currentX, 0f),
        ctx = ctx,
        lines = state.data.business,
        state = state,
        measureOnly = true
    )

    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = state.color.primary)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )

    // --- Calculate layout ---
    val maxContentHeight = maxOf(logoBounds.height, fromBounds.height, headingBounds.height)


    val totalHeaderHeight = maxContentHeight + paddingVertical
    val baseY = topLeft.y

    // Center Y for each component
    val logoOffset =
        Offset(logoBounds.topLeft.x, baseY + (maxContentHeight - logoBounds.height) / 2f)
    val fromOffset =
        Offset(fromBounds.topLeft.x, baseY + (maxContentHeight - fromBounds.height) / 2f)

    //  Right-align the heading
    val headingX = topLeft.x + width - paddingHorizontal - headingBounds.width // - 5f * scale
    val headingY = baseY + (maxContentHeight - headingBounds.height) / 2f
    val headingOffsetCentered = Offset(headingX, headingY)

    // --- Final Draw Phase ---
    if (!measureOnly) {
        drawLogo(logoOffset, ctx, state, measureOnly = false)
        fromSection(fromOffset, ctx, state, state.data.business, measureOnly = false)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffsetCentered,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
            measureOnly = false
        )


        if (borderBottom) {
            // ✅ Bottom border line
            val borderY = topLeft.y + totalHeaderHeight
            drawLine(
                color = state.color.neutral3,
                start = Offset(topLeft.x + paddingHorizontal, borderY),
                end = Offset(topLeft.x + width - paddingHorizontal, borderY),
                strokeWidth = ctx.scaledDpSize(2f)
            )
        }
    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, totalHeaderHeight)
    )
}


fun DrawScope.drawStyledHeader(
    topLeft: Offset,
    width: Float,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    borderBottom: Boolean = true,
    paddingHorizontalDp: Float = 0f,
    paddingVerticalDp: Float = 0f,
    measureOnly: Boolean = false,
): DrawBounds {
    val paddingHorizontal = ctx.scaledDpSize(paddingHorizontalDp)
    val paddingVertical = ctx.scaledDpSize(paddingVerticalDp)

    // Start X for layout
    var currentX = topLeft.x + paddingHorizontal
    val gap = ctx.scaledDpSize(60f)

    // 1. Measure Logo
    val logoBounds = drawLogo(
        topLeft = Offset(currentX, 0f), ctx = ctx, state = state, measureOnly = true
    )

    currentX += logoBounds.width + ctx.scaledDpSize(60f)


    val invoiceTitleStyles =
        state.style.invoiceTitle.copy(fontFamily = state.fontFamily, color = state.color.neutral2)


    // 3. Measure Heading
    val headingBounds = drawStyledHeading(
        text = state.data.title, topLeft = Offset.Zero, // placeholder
        originalStyle = invoiceTitleStyles, ctx = ctx, measureOnly = true
    )


    val recWidth = width * 6 / 8
    val recHeight = gap + logoBounds.height + gap

    val headerBoxBounds = drawBottomRoundedRect(
        color = Color(0xFFE3E3E3),
        Offset(topLeft.x + (width - recWidth) / 2, topLeft.y),
        Size(recWidth, recHeight),
        cornerRadius = 20f,
        measureOnly = true
    )

    val logoTitleWidth = logoBounds.width + gap + headingBounds.width

    val logoOffsetX = headerBoxBounds.topLeft.x + (headerBoxBounds.width - logoTitleWidth) / 2f
    val logoOffsetY = headerBoxBounds.topLeft.y + (headerBoxBounds.height - logoBounds.height) / 2f

    val headingOffsetX = logoOffsetX + logoBounds.width + gap
    val headingOffsetY =
        headerBoxBounds.topLeft.y + (headerBoxBounds.height - headingBounds.height) / 2f

    // Center Y for each component
    val logoOffset = Offset(logoOffsetX, logoOffsetY)
    val headingOffsetCentered = Offset(headingOffsetX, headingOffsetY)

    var newCurrentX = topLeft.x


    val headingStyle = ctx.scaledStyle(
        state.style.detailLabel.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )

    val valueStyle = ctx.scaledStyle(
        state.style.detailText.copy(
            fontFamily = state.fontFamily, color = state.color.neutral2
        )
    )


    val fromEntries = mutableMapOf<String, TextLayoutResult>()
    state.data.business.forEachIndexed { index, value ->
        if (index == 0) fromEntries.put(value, ctx.textMeasurer.measure(value, headingStyle))
        else fromEntries.put(value, ctx.textMeasurer.measure(value, valueStyle))
    }


    val val1TextMeasure = ctx.textMeasurer.measure(state.data.business[0], headingStyle)
    val val2TextMeasure = ctx.textMeasurer.measure(state.data.business[1], valueStyle)
    val val3TextMeasure = ctx.textMeasurer.measure(state.data.business[2], valueStyle)
    val val4TextMeasure = ctx.textMeasurer.measure(state.data.business[3], valueStyle)
    val val5TextMeasure = ctx.textMeasurer.measure(state.data.business[4], valueStyle)
    val val6TextMeasure = ctx.textMeasurer.measure(state.data.business[5], valueStyle)

    val maxBlock1Height =
        val1TextMeasure.size.height + val2TextMeasure.size.height + val3TextMeasure.size.height

    val maxBlock2Height = val2TextMeasure.size.height + val3TextMeasure.size.height
    val maxBlock3Height = val2TextMeasure.size.height

    val fromBlock1 = drawBottomRoundedRect(
        color = Color.Red,
        Offset(newCurrentX, headerBoxBounds.bottom + gap),
        Size(width * 2 / 7, maxBlock1Height.toFloat()),
        cornerRadius = 0f,
        measureOnly = true
    )

    drawLine(
        color = state.color.neutral3,
        start = Offset(fromBlock1.topLeft.x + fromBlock1.width, fromBlock1.topLeft.y),
        end = Offset(
            fromBlock1.topLeft.x + fromBlock1.width, fromBlock1.topLeft.y + fromBlock1.height
        ),
        strokeWidth = ctx.scaledDpSize(2f)
    )


    newCurrentX += fromBlock1.width

    val fromBlock2 = drawBottomRoundedRect(
        color = Color.Green,
        Offset(newCurrentX, headerBoxBounds.bottom + gap + (maxBlock1Height - maxBlock2Height) / 2),
        Size(width * 3 / 7, maxBlock2Height.toFloat()),
        cornerRadius = 0f,
        measureOnly = true
    )

    drawLine(
        color = state.color.neutral3,
        start = Offset(fromBlock2.topLeft.x + fromBlock2.width, fromBlock1.topLeft.y),
        end = Offset(
            fromBlock2.topLeft.x + fromBlock2.width, fromBlock1.topLeft.y + fromBlock1.height
        ),
        strokeWidth = ctx.scaledDpSize(2f)
    )


    newCurrentX += fromBlock2.width

    val fromBlock3 = drawBottomRoundedRect(
        color = Color.Blue,
        Offset(newCurrentX, headerBoxBounds.bottom + gap + (maxBlock1Height - maxBlock3Height) / 2),
        Size(width * 2 / 7, maxBlock3Height.toFloat()),
        cornerRadius = 0f,
        measureOnly = true
    )

    var fromCurrentX = fromBlock1.topLeft.x
    var fromCurrentY = fromBlock1.topLeft.y

    drawText(
        textLayoutResult = val1TextMeasure, topLeft = Offset(
            fromCurrentX + (fromBlock1.width - val1TextMeasure.size.width) / 2, fromCurrentY
        )
    )

    fromCurrentY += val1TextMeasure.size.height // + ctx.scaledDpSize(5f)


    drawText(
        textLayoutResult = val2TextMeasure, topLeft = Offset(
            fromCurrentX + (fromBlock1.width - val2TextMeasure.size.width) / 2, fromCurrentY
        )
    )

    fromCurrentY += val2TextMeasure.size.height // + ctx.scaledDpSize(5f)

    drawText(
        textLayoutResult = val3TextMeasure, topLeft = Offset(
            fromCurrentX + (fromBlock1.width - val3TextMeasure.size.width) / 2, fromCurrentY
        )
    )


    // Box 2

    fromCurrentX = fromBlock2.topLeft.x
    fromCurrentY = fromBlock2.topLeft.y

    drawText(
        textLayoutResult = val4TextMeasure, topLeft = Offset(
            fromCurrentX + (fromBlock2.width - val4TextMeasure.size.width) / 2, fromCurrentY
        )
    )

    fromCurrentY += val4TextMeasure.size.height // + ctx.scaledDpSize(5f)


    drawText(
        textLayoutResult = val5TextMeasure, topLeft = Offset(
            fromCurrentX + (fromBlock2.width - val5TextMeasure.size.width) / 2, fromCurrentY
        )
    )


    // Box 3

    fromCurrentX = fromBlock3.topLeft.x
    fromCurrentY = fromBlock3.topLeft.y

    drawText(
        textLayoutResult = val6TextMeasure, topLeft = Offset(
            fromCurrentX + (fromBlock3.width - val6TextMeasure.size.width) / 2, fromCurrentY
        )
    )

    val endY = fromBlock1.topLeft.y + fromBlock1.height + paddingVertical


    drawLine(
        color = state.color.neutral3,
        start = Offset(topLeft.x, endY),
        end = Offset(topLeft.x + width, endY),
        strokeWidth = ctx.scaledDpSize(2f)
    )


    // --- Final Draw Phase ---
    if (!measureOnly) {

        drawBottomRoundedRect(
            color = Color(0xFFE3E3E3),
            Offset(topLeft.x + (width - recWidth) / 2, topLeft.y),
            Size(recWidth, recHeight),
            cornerRadius = 20f,
        )


        drawLogo(logoOffset, ctx, state, black = true, measureOnly = false)
        drawStyledHeading(
            text = state.data.title,
            topLeft = headingOffsetCentered,
            originalStyle = invoiceTitleStyles,
            ctx = ctx,
            measureOnly = false
        )

    }

    return DrawBounds(
        topLeft = topLeft, size = Size(width, endY)
    )
}