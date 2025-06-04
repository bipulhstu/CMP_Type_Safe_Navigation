package org.bipul.cmptypesafe

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP Type Safe Navigation",
    ) {
        App()
    }
}