package org.apptrick.invotick.presentation.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.compositionLocalOf
import org.apptrick.invotick.data.model.InvoiceTemplateConfig

class InvoiceTemplateState(
    config: InvoiceTemplateConfig
) {
    var style by mutableStateOf(config.style)
    var color by mutableStateOf(config.color)
    var visibility by mutableStateOf(config.visibility)
    var data by mutableStateOf(config.data)
    var fontFamily by mutableStateOf(config.fontFamily)
    var templateType by mutableStateOf(config.templateType)
    var images by mutableStateOf(config.images)

    // Optional: save the original template if you want to "reset"
    val originalTemplate = config
}

val LocalInvoiceTemplateState = compositionLocalOf<InvoiceTemplateState> {
    error("InvoiceTemplateState not provided")
}
