package org.apptrick.invotick

import androidx.compose.ui.window.ComposeUIViewController
import org.apptrick.invotick.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
) { App() }