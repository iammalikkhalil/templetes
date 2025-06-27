package org.apptrick.invotick.presentation.helper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import invotick.composeapp.generated.resources.Res
import invotick.composeapp.generated.resources.signature
import invotick.composeapp.generated.resources.stamp
import invotick.composeapp.generated.resources.templete2Bg
import org.apptrick.invotick.data.model.InvoiceBitmaps
import org.apptrick.invotick.data.model.InvoiceImageRes
import org.apptrick.invotick.data.model.InvoiceTemplateConfig
import org.apptrick.invotick.data.model.InvoiceTextStyles
import org.apptrick.invotick.data.model.InvoiceVisibilities
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.presentation.theme.generateInvoiceColors

fun createTemplate7(
    fontFamily: FontFamily, images: InvoiceBitmaps
): InvoiceTemplateConfig {
    return InvoiceTemplateConfig(
        color = generateInvoiceColors(Color(0xFFAD4800)),
        style = invoiceTemp7TextStyles(),
        visibility = InvoiceVisibilities(),
        data = sampleData1(),
        fontFamily = fontFamily,
        templateType = TemplateType.TEMPLATE_7,
        images = images,
    )
}


fun template7ImageRes(): InvoiceImageRes = InvoiceImageRes(
    headerBackground = Res.drawable.templete2Bg,
    signature = Res.drawable.signature,
    stamp = Res.drawable.stamp,
)

fun invoiceTemp7TextStyles(): InvoiceTextStyles = InvoiceTextStyles(
//    invoiceTitle = defaultTextStyle(150f, FontWeight.ExtraBold),
)