package org.apptrick.invotick.data.model

import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.compose.resources.DrawableResource

data class InvoiceBitmaps(
    val logo: ImageBitmap? = null,
    val stamp: ImageBitmap? = null,
    val signature: ImageBitmap? = null,
    val background: ImageBitmap? = null,
    val headerBackground: ImageBitmap? = null,
    val footerBackground: ImageBitmap? = null,
)



data class InvoiceImageRes(
    val logo: DrawableResource? = null,
    val stamp: DrawableResource? = null,
    val signature: DrawableResource? = null,
    val background: DrawableResource? = null,
    val headerBackground: DrawableResource? = null,
    val footerBackground: DrawableResource? = null
)