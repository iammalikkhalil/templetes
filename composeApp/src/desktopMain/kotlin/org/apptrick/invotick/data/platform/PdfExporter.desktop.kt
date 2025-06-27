package org.apptrick.invotick.data.platform

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PdfExporter actual constructor() {
    actual fun exportPdf(
        fileName: String,
        widthPx: Int,
        heightPx: Int,
        draw: DrawScope.() -> Unit,
        imageBitmap: ImageBitmap
    ) {
    }
}