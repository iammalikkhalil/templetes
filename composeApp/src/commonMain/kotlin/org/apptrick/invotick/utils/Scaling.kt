package org.apptrick.invotick.utils


import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


val LocalScaleFactor = compositionLocalOf { 1f }

@Composable
fun Int.scale(): Dp = (this * LocalScaleFactor.current).dp

@Composable
fun Float.scale(): Dp = (this * LocalScaleFactor.current).dp

@Composable
fun Int.spScaled() = (this * LocalScaleFactor.current).sp

@Composable
fun Double.spScaled() = (this * LocalScaleFactor.current).sp
