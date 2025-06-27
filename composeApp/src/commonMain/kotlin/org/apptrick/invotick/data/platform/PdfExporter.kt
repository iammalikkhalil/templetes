package org.apptrick.invotick.data.platform

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class PdfExporter() {
    fun exportPdf(fileName: String, widthPx: Int, heightPx: Int, draw: DrawScope.() -> Unit, imageBitmap: ImageBitmap)
}