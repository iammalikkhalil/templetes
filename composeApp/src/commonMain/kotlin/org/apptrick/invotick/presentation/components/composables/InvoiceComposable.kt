package org.apptrick.invotick.presentation.components.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.data.model.A4BoxSize
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.presentation.state.InvoiceTemplateState
import org.apptrick.invotick.presentation.templetes.invoiceTemplate10f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate1f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate2f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate3f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate4f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate5f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate6f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate7f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate8f
import org.apptrick.invotick.presentation.templetes.invoiceTemplate9f

@Composable
fun InvoiceComposable(
    state: InvoiceTemplateState, modifier: Modifier = Modifier.fillMaxSize()
) {
    BoxWithConstraints(modifier = modifier) {

        val result = rememberInvoiceRenderResult(state, maxWidth, maxHeight)
        val a4Size = result.a4Size
        val drawFn = result.draw

        val canvasModifier =
            Modifier.width(a4Size.boxWidthDp).height(a4Size.boxHeightDp).background(Color.White)

        Canvas(modifier = canvasModifier) {
            drawFn()
        }
    }
}

@Composable
fun rememberA4ScaledSize(
    maxWidth: Dp, maxHeight: Dp, density: Density
): A4BoxSize {
    val parentWidthPx = with(density) { maxWidth.toPx() }
    val parentHeightPx = with(density) { maxHeight.toPx() }

    val a4Ratio = 1.4142f
    val (boxWidthPx, boxHeightPx) = if (parentWidthPx < parentHeightPx) {
        val idealHeight = parentWidthPx * a4Ratio
        if (idealHeight <= parentHeightPx) {
            parentWidthPx to idealHeight
        } else {
            val adjustedWidth = parentHeightPx / a4Ratio
            adjustedWidth to parentHeightPx
        }
    } else {
        val adjustedWidth = parentHeightPx / a4Ratio
        adjustedWidth to parentHeightPx
    }

    val boxWidthDp = with(density) { boxWidthPx.toDp() }
    val boxHeightDp = with(density) { boxHeightPx.toDp() }
    val scale = boxWidthDp.value / 2380f


    return A4BoxSize(boxWidthDp, boxHeightDp, boxWidthPx, boxHeightPx, scale)
}


@Composable
fun rememberInvoiceRenderResult(
    state: InvoiceTemplateState, maxWidth: Dp = 2380f.dp, maxHeight: Dp = 3365.796f.dp
): InvoiceRenderResult {
    val density = LocalDensity.current
    val a4Size = rememberA4ScaledSize(maxWidth, maxHeight, density)

    val boxWidthPx = a4Size.boxWidthPx
    val boxHeightPx = a4Size.boxHeightPx
    val scale = a4Size.scale

    val textMeasurer = rememberTextMeasurer()
    val ctx = remember(density, scale, textMeasurer) {
        InvoiceRenderContext(density, scale, textMeasurer)
    }

    // ✅ Explicitly declare lambda type here to fix the error
    val draw: DrawScope.() -> Unit = remember(state, boxWidthPx, boxHeightPx, ctx) {
        when (state.templateType) {
            TemplateType.TEMPLATE_1 -> {
                { invoiceTemplate1f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_2 -> {
                { invoiceTemplate2f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_3 -> {
                { invoiceTemplate3f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_4 -> {
                { invoiceTemplate4f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_5 -> {
                { invoiceTemplate5f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_6 -> {
                { invoiceTemplate6f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_7 -> {
                { invoiceTemplate7f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_8 -> {
                { invoiceTemplate8f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_9 -> {
                { invoiceTemplate9f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            TemplateType.TEMPLATE_10 -> {
                { invoiceTemplate10f(boxWidthPx, boxHeightPx, ctx, state) }
            }

            /*else -> {
                { drawRect(color = Color.Red, size = this.size) }
            }*/
        }
    }

    return InvoiceRenderResult(
        a4Size = a4Size, draw = draw
    )
}

data class InvoiceRenderResult(
    val a4Size: A4BoxSize, val draw: DrawScope.() -> Unit
)

