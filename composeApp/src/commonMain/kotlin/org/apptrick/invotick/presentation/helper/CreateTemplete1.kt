package org.apptrick.invotick.presentation.helper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import invotick.composeapp.generated.resources.Res
import invotick.composeapp.generated.resources.signature
import invotick.composeapp.generated.resources.templete1Bg
import org.apptrick.invotick.data.model.InvoiceBitmaps
import org.apptrick.invotick.data.model.InvoiceImageRes
import org.apptrick.invotick.data.model.InvoiceTemplateConfig
import org.apptrick.invotick.data.model.InvoiceTextStyles
import org.apptrick.invotick.data.model.InvoiceVisibilities
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.presentation.theme.generateInvoiceColors

fun createTemplate1(
    fontFamily: FontFamily, images: InvoiceBitmaps
): InvoiceTemplateConfig {
    return InvoiceTemplateConfig(
        color = generateInvoiceColors(Color.Black),
        style = invoiceTemp1TextStyles(),
        visibility = InvoiceVisibilities(),
        data = sampleData1("INVOICE"),
        fontFamily = fontFamily,
        templateType = TemplateType.TEMPLATE_1,
        images = images,
    )
}

fun template1ImageRes(): InvoiceImageRes = InvoiceImageRes(
    background = Res.drawable.templete1Bg, signature = Res.drawable.signature
)

fun invoiceTemp1TextStyles(): InvoiceTextStyles = InvoiceTextStyles(
//    invoiceTitle = defaultTextStyle(150f, FontWeight.ExtraBold),
)