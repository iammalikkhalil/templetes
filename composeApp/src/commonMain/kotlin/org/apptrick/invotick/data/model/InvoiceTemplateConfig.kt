package org.apptrick.invotick.data.model

import androidx.compose.ui.text.font.FontFamily

data class InvoiceTemplateConfig(
    val color: InvoiceColors,
    val style: InvoiceTextStyles,
    val visibility: InvoiceVisibilities,
    val data: InvoiceData,
    val fontFamily: FontFamily,
    val templateType: TemplateType,
    val images: InvoiceBitmaps = InvoiceBitmaps() // 👈 added
)

enum class TemplateType(val displayName: String) {
    TEMPLATE_1("Template 1"),
    TEMPLATE_2("Template 2"),
    TEMPLATE_3("Template 3"),
    TEMPLATE_4("Template 4"),
    TEMPLATE_5("Template 5"),
    TEMPLATE_6("Template 6"),
    TEMPLATE_7("Template 7"),
    TEMPLATE_8("Template 8"),
    TEMPLATE_9("Template 9"),
    TEMPLATE_10("Template 10"),
}
