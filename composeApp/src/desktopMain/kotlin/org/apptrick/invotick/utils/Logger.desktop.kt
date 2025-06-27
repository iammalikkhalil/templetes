package org.apptrick.invotick.utils

import org.apptrick.invotick.utils.Logger

// Expect actual singleton
actual object PlatformLogger : Logger {
    actual override fun logInfo(message: String) {
    }

    actual override fun logError(message: String, throwable: Throwable?) {
    }
}