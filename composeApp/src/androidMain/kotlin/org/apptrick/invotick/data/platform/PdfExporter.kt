// androidMain/pdf/PdfExporter.kt
package org.apptrick.invotick.data.platform

import android.content.ContentValues
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.graphics.Canvas as AndroidCanvas
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.apptrick.invotick.MyApplication
import java.io.File
import java.io.FileOutputStream

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PdfExporter {
    actual fun exportPdf(
        fileName: String,
        widthPx: Int,
        heightPx: Int,
        draw: DrawScope.() -> Unit,
        imageBitmap: ImageBitmap
    ) {
        val context = MyApplication.appContext
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(widthPx, heightPx, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val androidCanvas: AndroidCanvas = page.canvas
        val canvas = Canvas(androidCanvas)

        val drawScope = CanvasDrawScope()
        val density = context.resources.displayMetrics.density

        drawScope.draw(
            density = Density(density),
            layoutDirection = LayoutDirection.Ltr,
            canvas = canvas,
            size = Size(widthPx.toFloat(), heightPx.toFloat()),
            block = draw
        )

        pdfDocument.finishPage(page)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // ✅ API 29+ (Android 10+) — Use MediaStore
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Download")
            }

            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

            if (uri != null) {
                resolver.openOutputStream(uri)?.use { outputStream ->
                    pdfDocument.writeTo(outputStream)
                }
            } else {
                throw IllegalStateException("Failed to create PDF in Downloads folder.")
            }

        } else {
            // ✅ API 21–28 — Use legacy Downloads directory
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val outputFile = File(downloadsDir, "$fileName.pdf")

            // Create parent directories if missing
            outputFile.parentFile?.mkdirs()

            FileOutputStream(outputFile).use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
        }

        pdfDocument.close()
    }
}