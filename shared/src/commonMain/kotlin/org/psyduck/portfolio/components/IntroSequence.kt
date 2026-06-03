package org.psyduck.portfolio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.psyduck.portfolio.theme.rememberJetBrainsMono
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun IntroSequence() {

    var stage by remember { mutableStateOf(0) }
    var lineCount by remember { mutableStateOf(0) }

    val intro = listOf(
        "Om Anand",
    )

    LaunchedEffect(Unit) {
        delay(1200.milliseconds)
        stage = 1
        delay(600.milliseconds)
        stage = 2
    }

    Column {
        TypeWriterText(
            fullText = "whoami",
            showCursor = stage == 0
        )

        Spacer(Modifier.height(10.dp))

        if (stage >= 1) {
            Column {
                Text(
                    text = "Hi, I'm Om Anand!, Welcome to my portfolio...",
                    color = Color.White,
                    fontFamily = rememberJetBrainsMono()
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }

    if (stage >= 2) {
        TypeWriterText(fullText = "fastfetch", showCursor = true)
        Spacer(Modifier.height(10.dp))
        FastfetchSection()
    }
}
