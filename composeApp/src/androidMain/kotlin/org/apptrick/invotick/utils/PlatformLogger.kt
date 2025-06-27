package org.apptrick.invotick.utils

import android.util.Log

actual object PlatformLogger : Logger {
    private const val TAG = "KMPLogger"


    actual override fun logInfo(message: String) {
        Log.i(TAG, message)
    }

    actual override fun logError(message: String, throwable: Throwable?) {
        Log.e(TAG, message, throwable)
    }
}
