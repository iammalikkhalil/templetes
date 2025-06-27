@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.apptrick.invotick.utils


interface Logger {
    fun logInfo(message: String)
    fun logError(message: String, throwable: Throwable? = null)
}

// Expect actual singleton
expect object PlatformLogger : Logger {
    override fun logInfo(message: String)
    override fun logError(message: String, throwable: Throwable?)
}