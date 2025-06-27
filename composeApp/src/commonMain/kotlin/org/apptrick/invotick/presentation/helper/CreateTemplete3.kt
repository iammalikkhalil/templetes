package org.apptrick.invotick.presentation.helper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import invotick.composeapp.generated.resources.Res
import invotick.composeapp.generated.resources.signature
import invotick.composeapp.generated.resources.stamp
import org.apptrick.invotick.data.model.InvoiceBitmaps
import org.apptrick.invotick.data.model.InvoiceImageRes
import org.apptrick.invotick.data.model.InvoiceTemplateConfig
import org.apptrick.invotick.data.model.InvoiceTextStyles
import org.apptrick.invotick.data.model.InvoiceVisibilities
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.presentation.theme.generateInvoiceColors

fun createTemplate3(
    fontFamily: FontFamily, images: InvoiceBitmaps
): InvoiceTemplateConfig {
    return InvoiceTemplateConfig(
        color = generateInvoiceColors(Color(0xFF5236AF)),
        style = invoiceTemp3TextStyles(),
        visibility = InvoiceVisibilities(),
        data = sampleData1(),
        fontFamily = fontFamily,
        templateType = TemplateType.TEMPLATE_3,
        images = images,
    )
}


fun template3ImageRes(): InvoiceImageRes = InvoiceImageRes(
    signature = Res.drawable.signature,
    stamp = Res.drawable.stamp,
)

fun invoiceTemp3TextStyles(): InvoiceTextStyles = InvoiceTextStyles(
//    invoiceTitle = defaultTextStyle(350f, FontWeight.ExtraBold),
)