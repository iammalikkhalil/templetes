package org.apptrick.invotick.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.apptrick.invotick.data.model.TemplateType
import org.apptrick.invotick.presentation.components.composables.InvoiceComposable
import org.apptrick.invotick.presentation.components.composables.PdfGenerationScreen
import org.apptrick.invotick.presentation.fonts.NunitoFamily
import org.apptrick.invotick.presentation.manager.InvoiceTemplateManager
import org.apptrick.invotick.presentation.state.LocalInvoiceTemplateState

@Composable
fun MainScreen() {
    val fontFamily = NunitoFamily()
    val manager = remember { InvoiceTemplateManager() }
    val selectedTemplate = remember { mutableStateOf(TemplateType.TEMPLATE_1) }
    val isInitialized = remember { mutableStateOf(false) }

    // Initialize once
    if (!isInitialized.value) {
        manager.initialize(fontFamily)
        manager.switchTemplate(selectedTemplate.value) // Enum-based switch
        isInitialized.value = true
    }

    val template = manager.activeTemplate.value ?: return

    CompositionLocalProvider(LocalInvoiceTemplateState provides template) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Gray).safeContentPadding()
                .padding(16.dp)
        ) {
            // Template switch buttons

            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
            ) {
                TemplateType.entries.forEach { type ->
                    TemplateButton(
                        label = type.displayName, selected = selectedTemplate.value == type
                    ) {
                        selectedTemplate.value = type
                        manager.switchTemplate(type)
                    }
                }
            }


            Spacer(modifier = Modifier.height(12.dp))

            PdfGenerationScreen(state = template)

            Spacer(modifier = Modifier.height(12.dp))

            // Invoice preview
            InvoiceComposable(template)
        }
    }
}

@Composable
fun TemplateButton(label: String, selected: Boolean, onClick: () -> Unit) {
    val background = if (selected) Color.Black else Color.LightGray
    val textColor = if (selected) Color.White else Color.Black

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = background),
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Text(text = label, color = textColor)
    }
}