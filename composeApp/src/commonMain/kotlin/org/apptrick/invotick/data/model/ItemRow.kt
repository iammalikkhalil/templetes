package org.apptrick.invotick.data.model

data class ItemRow(
    val description: String,
    val qty: Int,
    val price: String,
    val discount: String,
    val tax: String,
    val amount: String
)