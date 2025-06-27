package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import org.apptrick.invotick.core.InvoiceRenderContext
import org.apptrick.invotick.core.model.DrawBounds
import org.apptrick.invotick.core.scaledDpSize
import org.apptrick.invotick.core.scaledStyle
import org.apptrick.invotick.presentation.state.InvoiceTemplateState
import org.apptrick.invotick.utils.PlatformLogger
import kotlin.math.roundToInt

fun DrawScope.drawUnpaidStamp(
    topLeft: Offset,
    ctx: InvoiceRenderContext,
    state: InvoiceTemplateState,
    width: Float,
): DrawBounds {
    val cornerRadius = ctx.scaledDpSize(45f)
    val cornerRadius2 = ctx.scaledDpSize(32f)
    val outerBorderStroke = ctx.scaledDpSize(20f)
    val innerBorderStroke = ctx.scaledDpSize(5f)
    val gap = ctx.scaledDpSize(10f)
    val text = "UNPAID"  //Hardcode only this
    val height = width * 0.32f
    val style = state.style.invoiceTitle.copy(
        fontFamily = FontFamily.Serif,
        color = state.color.primary,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 100.sp
    )
    val scaledStyle = ctx.scaledStyle(style)
    val layoutResult = ctx.textMeasurer.measure(text.uppercase(), scaledStyle)


    val outerBorderOffset = topLeft

    val outerBorderSize = Size(
        width, height
    )

    drawIntoCanvas { canvas ->

        val paint = Paint().apply {
            this.blendMode = BlendMode.Clear
            this.color = Color.Red
            this.strokeWidth = innerBorderStroke
        }



        drawRoundRect(
            color = state.color.primary,
            outerBorderOffset,
            outerBorderSize,
            CornerRadius(cornerRadius),
            style = Stroke(width = outerBorderStroke),
        )


        val innerBorderOffset = Offset(
            outerBorderOffset.x + outerBorderStroke / 2 + gap,
            outerBorderOffset.y + outerBorderStroke / 2 + gap
        )

        val innerBorderSize = Size(
            width = outerBorderSize.width - 2 * gap - outerBorderStroke,
            height = outerBorderSize.height - 2 * gap - outerBorderStroke,
        )

        canvas.drawRoundRect(
            left = innerBorderOffset.x,
            top = innerBorderOffset.y,
            innerBorderOffset.x + innerBorderSize.width,
            innerBorderOffset.y + innerBorderSize.height,
            cornerRadius2,
            cornerRadius2,
            paint = Paint().apply { strokeWidth = innerBorderStroke }
        )


        val textOffSet = Offset(
            outerBorderOffset.x + (outerBorderSize.width - layoutResult.size.width) / 2f,
            outerBorderOffset.y + (outerBorderSize.height - layoutResult.size.height) / 2f
        )

        drawText(textLayoutResult = layoutResult, topLeft = textOffSet)



//        state.images.noise?.let { image ->
//
//            canvas.drawImageRect(
//                image = image,
//                srcOffset = IntOffset.Zero,
//                srcSize = IntSize(image.width, image.height),
//                dstOffset = IntOffset(
//                    (topLeft.x - outerBorderStroke / 2).roundToInt(),
//                    (topLeft.y - outerBorderStroke / 2).roundToInt()
//                ),
//                dstSize = IntSize(
//                    (width + outerBorderStroke).roundToInt(),
//                    (height + outerBorderStroke).roundToInt()
//                ),
//                paint = paint
//            )
//        } ?: run {
//            PlatformLogger.logInfo("Image Not Found")
//        }

//    one of the following candidates is applicable: fun drawImage(image: ImageBitmap, topLeft: Offset = ..., alpha: Float = ..., style: DrawStyle = ..., colorFilter: ColorFilter? = ..., blendMode: BlendMode = ...): Unit fun drawImage(image: ImageBitmap, srcOffset: IntOffset = ..., srcSize: IntSize = ..., dstOffset: IntOffset = ..., dstSize: IntSize = ..., alpha: Float = ..., style: DrawStyle = ..., colorFilter: ColorFilter? = ..., blendMode: BlendMode = ..., filterQuality: FilterQuality = ...): Unit

    }

    return DrawBounds(topLeft = topLeft, size = outerBorderSize)
}
