package org.apptrick.invotick.data.model


data class InvoiceVisibilities(
    val showLogo: Boolean = true,
    val showTitle: Boolean = true,
    val showStamp: Boolean = false,
    val showSignature: Boolean = false,
    val showDetailSection: Boolean = true,
    val showFromSection: Boolean = true,
    val showToSection: Boolean = true,
    val showItemTable: Boolean = true,
    val showSummarySection: Boolean = true,
    val showPaymentMethod: Boolean = true,
    val showTermsAndConditions: Boolean = true,
)
