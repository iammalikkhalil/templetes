package org.apptrick.invotick.core.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

data class DrawBounds(
    val topLeft: Offset,
    val size: Size
) {
    val width get() = size.width
    val height get() = size.height
    val bottom get() = topLeft.y + size.height
    val right get() = topLeft.x + size.width
    val center get() = Offset(topLeft.x + size.width / 2, topLeft.y + size.height / 2)
}


fun mergeBounds(a: DrawBounds, b: DrawBounds): DrawBounds {
    val left = minOf(a.topLeft.x, b.topLeft.x)
    val top = minOf(a.topLeft.y, b.topLeft.y)
    val right = maxOf(a.right, b.right)
    val bottom = maxOf(a.bottom, b.bottom)
    return DrawBounds(Offset(left, top), Size(right - left, bottom - top))
}
