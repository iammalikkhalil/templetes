package org.apptrick.invotick.presentation.manager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.FontFamily
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.presentation.helper.createTemplate1
import org.apptrick.invotick.presentation.helper.createTemplate10
import org.apptrick.invotick.presentation.helper.createTemplate2
import org.apptrick.invotick.presentation.helper.createTemplate3
import org.apptrick.invotick.presentation.helper.createTemplate4
import org.apptrick.invotick.presentation.helper.createTemplate5
import org.apptrick.invotick.presentation.helper.createTemplate6
import org.apptrick.invotick.presentation.helper.createTemplate7
import org.apptrick.invotick.presentation.helper.createTemplate8
import org.apptrick.invotick.presentation.helper.createTemplate9
import org.apptrick.invotick.presentation.helper.loadTemplateImages
import org.apptrick.invotick.presentation.helper.template10ImageRes
import org.apptrick.invotick.presentation.helper.template1ImageRes
import org.apptrick.invotick.presentation.helper.template2ImageRes
import org.apptrick.invotick.presentation.helper.template3ImageRes
import org.apptrick.invotick.presentation.helper.template4ImageRes
import org.apptrick.invotick.presentation.helper.template5ImageRes
import org.apptrick.invotick.presentation.helper.template6ImageRes
import org.apptrick.invotick.presentation.helper.template7ImageRes
import org.apptrick.invotick.presentation.helper.template8ImageRes
import org.apptrick.invotick.presentation.helper.template9ImageRes
import org.apptrick.invotick.presentation.state.InvoiceTemplateState

class InvoiceTemplateManager {

    // Currently selected template
    val activeTemplate = mutableStateOf<InvoiceTemplateState?>(null)

    // Internal map for switching
    private var templates: Map<TemplateType, InvoiceTemplateState> = emptyMap()


    @Composable
    fun initialize(fontFamily: FontFamily) {

        val template1 = InvoiceTemplateState(
            createTemplate1(
                fontFamily, loadTemplateImages(template1ImageRes())
            )
        )

        val template2 = InvoiceTemplateState(
            createTemplate2(
                fontFamily, loadTemplateImages(template2ImageRes())
            )
        )

        val template3 = InvoiceTemplateState(
            createTemplate3(
                fontFamily, loadTemplateImages(template3ImageRes())
            )
        )

        val template4 = InvoiceTemplateState(
            createTemplate4(
                fontFamily, loadTemplateImages(template4ImageRes())
            )
        )


        val template5 = InvoiceTemplateState(
            createTemplate5(
                fontFamily, loadTemplateImages(template5ImageRes())
            )
        )


        val template6 = InvoiceTemplateState(
            createTemplate6(
                fontFamily, loadTemplateImages(template6ImageRes())
            )
        )


        val template7 = InvoiceTemplateState(
            createTemplate7(
                fontFamily, loadTemplateImages(template7ImageRes())
            )
        )


        val template8 = InvoiceTemplateState(
            createTemplate8(
                fontFamily, loadTemplateImages(template8ImageRes())
            )
        )


        val template9 = InvoiceTemplateState(
            createTemplate9(
                fontFamily, loadTemplateImages(template9ImageRes())
            )
        )

        val template10 = InvoiceTemplateState(
            createTemplate10(
                fontFamily, loadTemplateImages(template10ImageRes())
            )
        )


        templates = mapOf(
            TemplateType.TEMPLATE_1 to template1,
            TemplateType.TEMPLATE_2 to template2,
            TemplateType.TEMPLATE_3 to template3,
            TemplateType.TEMPLATE_4 to template4,
            TemplateType.TEMPLATE_5 to template5,
            TemplateType.TEMPLATE_6 to template6,
            TemplateType.TEMPLATE_7 to template7,
            TemplateType.TEMPLATE_8 to template8,
            TemplateType.TEMPLATE_9 to template9,
            TemplateType.TEMPLATE_10 to template10,
        )

        activeTemplate.value = template1 // Default
    }


    fun switchTemplate(type: TemplateType) {
        templates[type]?.let {
            activeTemplate.value = it
        }
    }

}