package org.apptrick.invotick.presentation.helper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import invotick.composeapp.generated.resources.Res
import invotick.composeapp.generated.resources.Subtract
import invotick.composeapp.generated.resources._5055
import invotick.composeapp.generated.resources.signature
import invotick.composeapp.generated.resources.stamp
import org.apptrick.invotick.data.model.InvoiceBitmaps
import org.apptrick.invotick.data.model.InvoiceImageRes
import org.apptrick.invotick.data.model.InvoiceTemplateConfig
import org.apptrick.invotick.data.model.InvoiceTextStyles
import org.apptrick.invotick.data.model.InvoiceVisibilities
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.presentation.theme.generateInvoiceColors

fun createTemplate2(
    fontFamily: FontFamily, images: InvoiceBitmaps
): InvoiceTemplateConfig {
    return InvoiceTemplateConfig(
        color = generateInvoiceColors(Color(0xFF702D3D)),
        style = invoiceTemp2TextStyles(),
        visibility = InvoiceVisibilities(),
        data = sampleData1(),
        fontFamily = fontFamily,
        templateType = TemplateType.TEMPLATE_2,
        images = images,
    )
}


fun template2ImageRes(): InvoiceImageRes = InvoiceImageRes(
    headerBackground = Res.drawable._5055,
    footerBackground = Res.drawable.Subtract,
    signature = Res.drawable.signature,
    stamp = Res.drawable.stamp,
)

fun invoiceTemp2TextStyles(): InvoiceTextStyles = InvoiceTextStyles(
//    invoiceTitle = defaultTextStyle(250f, FontWeight.ExtraBold),
)