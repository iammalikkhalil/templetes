package org.apptrick.invotick.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import org.apptrick.invotick.data.model.InvoiceColors
import kotlin.math.abs

fun generateInvoiceColors(
    primary: Color,
    secondaryLightness: Float = 0.8f // default lightness
): InvoiceColors {
    val neutral1 = getContrastTextColor(primary)
    val neutral2 = Color.Black
    val neutral3 = Color(0xFFBDBDBD)

    val secondary = generateSecondaryColor(primary, lightnessThreshold = secondaryLightness)

    return InvoiceColors(
        primary = primary,
        secondary = secondary,
        neutral1 = neutral1,
        neutral2 = neutral2,
        neutral3 = neutral3
    )
}


fun getContrastTextColor(background: Color): Color {
    return if (background.luminance() > 0.5f) Color.Black else Color.White
}



fun generateSecondaryColor(
    primary: Color,
    maxAttempts: Int = 10,
    lightnessThreshold: Float = 0.8f // <-- new parameter
): Color {
    val minBrightnessDelta = 0.15f
    val contrastTarget = 4.5

    var bestTint: Color? = null
    var bestHSL: Color? = null

    // Attempt to reach the target lightness threshold
    for (i in 1..maxAttempts) {
        val amount = (i / maxAttempts.toFloat()) * lightnessThreshold

        val tintCandidate = tint(primary, amount)
        val hslCandidate = lightenHSL(primary, amount)

        val tintContrast = calculateContrastRatio(Color.Black, tintCandidate)
        val hslContrast = calculateContrastRatio(Color.Black, hslCandidate)

        val tintDelta = tintCandidate.luminance() - primary.luminance()
        val hslDelta = hslCandidate.luminance() - primary.luminance()

        if (tintContrast >= contrastTarget && tintDelta >= minBrightnessDelta && bestTint == null) {
            bestTint = tintCandidate
        }

        if (hslContrast >= contrastTarget && hslDelta >= minBrightnessDelta && bestHSL == null) {
            bestHSL = hslCandidate
        }

        if (bestTint != null && bestHSL != null) break
    }

    return when {
        bestTint != null && bestHSL != null ->
            if (bestTint.luminance() > bestHSL.luminance()) bestTint else bestHSL

        bestHSL != null -> bestHSL
        bestTint != null -> bestTint

        else -> tint(primary, lightnessThreshold.coerceIn(0.0f, 1.0f))
    }
}



fun tint(color: Color, amount: Float): Color {
    return Color(
        red = (color.red + (1 - color.red) * amount).coerceIn(0f, 1f),
        green = (color.green + (1 - color.green) * amount).coerceIn(0f, 1f),
        blue = (color.blue + (1 - color.blue) * amount).coerceIn(0f, 1f),
        alpha = color.alpha
    )
}

fun lightenHSL(color: Color, amount: Float): Color {
    val (h, s, l) = color.toHSL()
    val newL = (l + amount).coerceIn(0f, 1f)
    return hslToColor(h, s, newL)
}

fun calculateContrastRatio(color1: Color, color2: Color): Double {
    val l1 = color1.luminance() + 0.05
    val l2 = color2.luminance() + 0.05
    return if (l1 > l2) l1 / l2 else l2 / l1
}

fun Color.toHSL(): FloatArray {
    val r = red
    val g = green
    val b = blue

    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val delta = max - min

    var h = 0f
    val s: Float
    val l = (max + min) / 2f

    if (delta == 0f) {
        h = 0f
        s = 0f
    } else {
        s = if (l < 0.5f) delta / (max + min) else delta / (2f - max - min)

        h = when (max) {
            r -> ((g - b) / delta + (if (g < b) 6 else 0))
            g -> ((b - r) / delta + 2)
            else -> ((r - g) / delta + 4)
        }
        h /= 6f
    }

    return floatArrayOf(h * 360f, s, l)
}

fun hslToColor(h: Float, s: Float, l: Float): Color {
    val c = (1 - abs(2 * l - 1)) * s
    val x = c * (1 - abs((h / 60f) % 2 - 1))
    val m = l - c / 2f

    val (r1, g1, b1) = when {
        h < 60f -> Triple(c, x, 0f)
        h < 120f -> Triple(x, c, 0f)
        h < 180f -> Triple(0f, c, x)
        h < 240f -> Triple(0f, x, c)
        h < 300f -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }

    return Color(
        red = (r1 + m).coerceIn(0f, 1f),
        green = (g1 + m).coerceIn(0f, 1f),
        blue = (b1 + m).coerceIn(0f, 1f),
        alpha = 1f
    )
}