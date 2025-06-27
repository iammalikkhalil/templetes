package org.apptrick.invotick.presentation.components.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import org.apptrick.invotick.data.platform.PdfExporter
import org.apptrick.invotick.presentation.helper.drawToImageBitmap
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

@Composable
fun PdfGenerationScreen(
    state: InvoiceTemplateState
) {
    var status by remember { mutableStateOf("Idle") }

    // ✅ Call this OUTSIDE the onClick lambda
    val result = rememberInvoiceRenderResult(state)
    val a4Size = result.a4Size
    val drawFn = result.draw

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            generatePdf(
                "invoice",
                a4Size.boxWidthPx.toInt(),
                a4Size.boxHeightPx.toInt(),
                drawFn,
            )
            status = "PDF Generated ✅"
        }) {
            Text("Generate PDF")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Status: $status")
    }
}





fun generatePdf(
    fileName: String,
    widthPx: Int,
    heightPx: Int,
    draw: DrawScope.() -> Unit
) {
    val exporter = PdfExporter()


    val imageBitmap = drawToImageBitmap(widthPx, heightPx, draw)


    exporter.exportPdf(fileName, widthPx, heightPx, draw, imageBitmap)
}

