package org.apptrick.invotick.presentation.components.drawings

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlin.math.roundToInt

fun DrawScope.drawImageScaled(
    image: ImageBitmap,
    topLeft: Offset,
    size: Size,
    alpha: Float = 1f
) {
    drawIntoCanvas { canvas ->

        val paint = Paint().apply {
            this.alpha = alpha
        }

        canvas.drawImageRect(
            image = image,
            srcOffset = IntOffset.Zero,
            srcSize = IntSize(image.width, image.height),
            dstOffset = IntOffset(topLeft.x.roundToInt(), topLeft.y.roundToInt()),
            dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt()),
            paint = paint
        )
    }
}