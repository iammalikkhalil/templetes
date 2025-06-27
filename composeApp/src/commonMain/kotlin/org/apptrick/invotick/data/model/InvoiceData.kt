package org.apptrick.invotick.data.model

data class InvoiceData(
    val logo: String = "",
    val title: String = "",
    val stamp: String = "",
    val signature: String = "",

    val detail: List<Pair<String, String>> = emptyList(),
    val business: List<String> = emptyList(),
    val client: List<String> = emptyList(),

    val itemTable: List<ItemRow> = emptyList(),
    val summary: List<Pair<String, String>> = emptyList(),

    val paymentMethod: List<String> = emptyList(),
    val termsAndConditions: List<String> = emptyList()
)