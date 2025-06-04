package org.bipul.cmptypesafe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform