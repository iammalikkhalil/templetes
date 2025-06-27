package org.apptrick.invotick.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class InvoiceTextStyles(
    val invoiceTitle: TextStyle = defaultTextStyle(180f, FontWeight.ExtraBold),
    val fromHeading3: TextStyle = defaultTextStyle(56f, FontWeight.SemiBold),
    val fromValue: TextStyle = defaultTextStyle(40f, FontWeight.Normal),
    val heading1: TextStyle = defaultTextStyle(64f, FontWeight.SemiBold),
    val heading2: TextStyle = defaultTextStyle(48f, FontWeight.Bold),
    val heading3: TextStyle = defaultTextStyle(80f, FontWeight.Bold),
    val detailLabel: TextStyle = defaultTextStyle(56f, FontWeight.W700),
    val detailText: TextStyle = defaultTextStyle(40f),
    val summaryLabel: TextStyle = defaultTextStyle(48f, FontWeight.W700),
    val summaryLastRow: TextStyle = defaultTextStyle(56f, FontWeight.ExtraBold),
    val summaryValue: TextStyle = defaultTextStyle(44f),
    val total: TextStyle = defaultTextStyle(56f, FontWeight.Bold, textAlign = TextAlign.Center),
    val value: TextStyle = defaultTextStyle(56f),
    val logoLabel: TextStyle = defaultTextStyle(64f, FontWeight.W700, color = Color.White)
)


fun defaultTextStyle(
    fontSize: Float = 48f,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Start
): TextStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = fontSize.sp,
    fontWeight = fontWeight,
    color = color,
    textAlign = textAlign
)