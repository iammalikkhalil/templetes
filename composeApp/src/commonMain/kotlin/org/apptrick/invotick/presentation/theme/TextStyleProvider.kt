package org.apptrick.invotick.presentation.theme


import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.apptrick.invotick.data.model.InvoiceTextStyles


class InvoiceStyleState {
    var styles by mutableStateOf(InvoiceTextStyles())
}

val LocalInvoiceStyleState = compositionLocalOf<InvoiceStyleState> {
    error("InvoiceStyleState not provided")
}

