package org.apptrick.invotick

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform