package org.apptrick.invotick.data.model

import androidx.compose.ui.graphics.Color

data class InvoiceColors(
    val primary: Color,
    var secondary: Color,
    val neutral1: Color, // for text over primary bg
    val neutral2: Color, // for main text (usually black)
    val neutral3: Color  // light gray for borders or muted text
)