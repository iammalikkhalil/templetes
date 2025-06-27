package org.apptrick.invotick.presentation.helper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import invotick.composeapp.generated.resources.Res
import invotick.composeapp.generated.resources.Vector47
import invotick.composeapp.generated.resources.signature
import invotick.composeapp.generated.resources.stamp
import org.apptrick.invotick.data.model.InvoiceBitmaps
import org.apptrick.invotick.data.model.InvoiceImageRes
import org.apptrick.invotick.data.model.InvoiceTemplateConfig
import org.apptrick.invotick.data.model.InvoiceTextStyles
import org.apptrick.invotick.data.model.InvoiceVisibilities
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.data.model.defaultTextStyle
import org.apptrick.invotick.presentation.theme.generateInvoiceColors

fun createTemplate5(
    fontFamily: FontFamily, images: InvoiceBitmaps
): InvoiceTemplateConfig {
    return InvoiceTemplateConfig(
        color = generateInvoiceColors(Color(0xFFE38D02)),
        style = invoiceTemp5TextStyles(),
        visibility = InvoiceVisibilities(),
        data = sampleData1(),
        fontFamily = fontFamily,
        templateType = TemplateType.TEMPLATE_5,
        images = images,
    )
}


fun template5ImageRes(): InvoiceImageRes = InvoiceImageRes(
    headerBackground = Res.drawable.Vector47,
    signature = Res.drawable.signature,
    stamp = Res.drawable.stamp,
)

fun invoiceTemp5TextStyles(): InvoiceTextStyles = InvoiceTextStyles(
    invoiceTitle = defaultTextStyle(150f, FontWeight.ExtraBold),
)