package org.apptrick.invotick.core

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

data class InvoiceRenderContext(
    val density: Density,
    val scale: Float,
    val textMeasurer: TextMeasurer,
)

fun InvoiceRenderContext.scaledStyle(style: TextStyle): TextStyle =
    style.copy(fontSize = (style.fontSize.value * scale).sp)

fun InvoiceRenderContext.scaledDpSize(value: Float): Float =
    (with(density) { value.dp.toPx() } * scale).roundToInt().toFloat()

fun InvoiceRenderContext.scaledRawSize(value: Float): Float = value * scale

fun Float.round(): Float = this.roundToInt().toFloat()