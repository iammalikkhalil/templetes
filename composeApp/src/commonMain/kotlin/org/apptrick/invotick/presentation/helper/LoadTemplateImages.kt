package org.apptrick.invotick.presentation.helper

import androidx.compose.runtime.Composable
import org.apptrick.invotick.data.model.InvoiceBitmaps
import org.apptrick.invotick.data.model.InvoiceImageRes
import org.jetbrains.compose.resources.imageResource

@Composable
fun loadTemplateImages(res: InvoiceImageRes): InvoiceBitmaps {
    return InvoiceBitmaps(
        logo = res.logo?.let { imageResource(it) },
        stamp = res.stamp?.let { imageResource(it) },
        signature = res.signature?.let { imageResource(it) },
        background = res.background?.let { imageResource(it) },
        headerBackground = res.headerBackground?.let { imageResource(it) },
        footerBackground = res.footerBackground?.let { imageResource(it) },
    )
}