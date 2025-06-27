package org.apptrick.invotick.presentation.helper

import org.apptrick.invotick.data.model.InvoiceData
import org.apptrick.invotick.data.model.ItemRow

fun sampleData(title: String = "INVOICE") = InvoiceData(
    logo = "",
    title = title,
    stamp = "",
    signature = "",
    detail = listOf("Invoice" to "AB123", "Date" to "19 June 2025"),
    business = listOf("From", "ABC Company", "Address", "Phone"),
    client = listOf("To", "Client Name", "Address", "Phone"),
    itemTable = listOf(ItemRow("Service", 2, "$100", "$5", "$0", "$205")),
    summary = listOf("TOTAL" to "$205"),
    paymentMethod = listOf("Bank Transfer", "Account Number"),
    termsAndConditions = listOf("Terms", "Pay within 7 days")
)

fun sampleData1(title: String = "INVOICE") = InvoiceData(
    logo = "", title = title, stamp = "", signature = "", detail = listOf(
        "Invoice" to "AB123",
        "Creation Date" to "14/9/2024",
        "Due Date" to "17/9/2024",
        "P.O. #" to "PO100056"
    ), business = listOf(
        "From",
        "John Smeeth",
        "Chief Director",
        "A : 5-1,Anson Road Singapore - 8989",
        "W : mail@mailid.com, www.myweb.com",
        "P : 880 - 12345 - 6789"
    ), client = listOf(
        "To",
        "MICHALE SMITH",
        "Employer",
        "B : 15205 North Kierland Blvd. Suite 100 - 9000",
        "W : mail@michale.com, www.myweb.com",
        "P : 765 - 12345 - 8259"
    ), itemTable = listOf(
        ItemRow("Apple Watch SE", 2, "RS100", "$5", "$0", "$205"),
        ItemRow("TP-LINK AC1750", 5, "RS100", "$5", "$0", "$205"),
        ItemRow("NINTENDO SWITCH", 10, "RS100", "$5", "$0", "$205"),
        ItemRow("ACER ASPIRE 5 SLIM", 20, "RS100", "$5", "$0", "$205")
    ), summary = listOf(
        "SUB TOTAL" to "$205",
        "DISCOUNT(5%)" to "$205",
        "TAX(VAT 5.5%)" to "$205",
        "SHIPPING" to "$205",
        "TOTAL" to "$205"
    ), paymentMethod = listOf(
        "Payment Method",
        "",
        "Address : 452-1 Rangpur Shapla-5600",
        "Paypal : yourmail@gmail.com",
        "Bank Details: AmyClark, Bank of USA"
    ), termsAndConditions = listOf("TERMS & CONDITIONS", "The payment is due within 7 days")
)