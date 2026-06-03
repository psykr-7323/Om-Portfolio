package org.psyduck.portfolio.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import org.psyduck.portfolio.theme.rememberJetBrainsMono

@Composable
fun TypeWriterText(
    fullText: String,
    showCursor : Boolean = true
) {
    val mono = rememberJetBrainsMono()

    var displayedText by remember { mutableStateOf("") }
    var blink by remember { mutableStateOf(true) }

    LaunchedEffect(fullText) {
        displayedText = ""
        fullText.forEach {
            displayedText += it
            delay(120)
        }
    }

    if (showCursor) {
        LaunchedEffect(Unit) {
            while (true) {
                blink = !blink
                delay(450)
            }
        }
    }

    Text(
        text = "Om@Portfolio:~$ $displayedText" + if (showCursor && blink) "▋" else "",
        color = Color.Green,
        fontFamily = mono
    )
}
