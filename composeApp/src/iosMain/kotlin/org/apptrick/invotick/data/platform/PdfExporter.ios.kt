package org.apptrick.invotick.data.platform

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.NativeCanvas
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIGraphicsBeginPDFContextToFile
import platform.UIKit.UIGraphicsBeginPDFPage
import platform.UIKit.UIGraphicsEndPDFContext
import platform.UIKit.UIGraphicsGetCurrentContext

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PdfExporter actual constructor() {
    @OptIn(ExperimentalForeignApi::class)
    actual fun exportPdf(
        fileName: String,
        widthPx: Int,
        heightPx: Int,
        draw: DrawScope.() -> Unit,
        imageBitmap: ImageBitmap
    ) {

        val docPath = NSSearchPathForDirectoriesInDomains(
            directory = NSDocumentDirectory,
            domainMask = NSUserDomainMask,
            expandTilde = true
        ).firstOrNull() as? String

        if (docPath == null) {
            println("❌ [PDFExporter] Could not get Documents directory.")
        }

        val fullPath = "$docPath/khalil123.pdf"
        println("📄 [PDFExporter] Saving invoice to: $fullPath")

        UIGraphicsBeginPDFContextToFile(fullPath, CGRectZero.readValue(), null)
        UIGraphicsBeginPDFPage()

        val cgContext = UIGraphicsGetCurrentContext()
        if (cgContext != null) {
            println("✅ [PDFExporter] Drawing invoice content...")
//            val canvas = Canvas(cgContext)

//            val nativeCanvas = NativeCanvas(imageBitmap)
            val canvas = Canvas(imageBitmap)


            val scope = CanvasDrawScope()
            scope.draw(
                density = Density(density = 2f), // Adjust DPI if needed
                layoutDirection = androidx.compose.ui.unit.LayoutDirection.Ltr,
                canvas = canvas,
                size = Size(widthPx.toFloat(), heightPx.toFloat()),
                block = draw
            )
        } else {
            println("❌ [PDFExporter] CGContext is null.")
            UIGraphicsEndPDFContext()
        }

        UIGraphicsEndPDFContext()
        println("✅ [PDFExporter] PDF saved successfully.")

    }
}