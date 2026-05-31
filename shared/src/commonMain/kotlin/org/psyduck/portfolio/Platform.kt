package org.psyduck.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform