package org.apptrick.invotick.presentation.helper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import invotick.composeapp.generated.resources.Layer1
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

fun createTemplate6(
    fontFamily: FontFamily, images: InvoiceBitmaps
): InvoiceTemplateConfig {
    return InvoiceTemplateConfig(
        color = generateInvoiceColors(Color(0xFF20C533)),
        style = invoiceTemp6TextStyles(),
        visibility = InvoiceVisibilities(),
        data = sampleData1(),
        fontFamily = fontFamily,
        templateType = TemplateType.TEMPLATE_6,
        images = images,
    )
}


fun template6ImageRes(): InvoiceImageRes = InvoiceImageRes(
    background = Res.drawable.Layer1,
    signature = Res.drawable.signature,
    stamp = Res.drawable.stamp,
)

fun invoiceTemp6TextStyles(): InvoiceTextStyles = InvoiceTextStyles(
//    invoiceTitle = defaultTextStyle(650f, FontWeight.ExtraBold),
)