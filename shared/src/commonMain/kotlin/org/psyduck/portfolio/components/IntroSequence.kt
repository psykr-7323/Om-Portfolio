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

// ─── Stages ──────────────────────────────────────────────────────────────────
// 0 → "whoami" typewriter running, cursor blinking
// 1 → greeting appears
// 2 → "fastfetch" + FastfetchSection appear
// 3 → "Explore my resume" appears
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun IntroSequence(
    skipAnimation: Boolean = false,
    onReadyToShowNav: () -> Unit = {}
) {

    var stage by remember { mutableStateOf(if (skipAnimation) 3 else 0) }

    // Normal animation flow
    LaunchedEffect(skipAnimation) {
        if (!skipAnimation && stage < 3) {
            delay(1200.milliseconds)
            stage = 1
            delay(500.milliseconds)
            stage = 2
            // Stage 3 triggered by FastfetchSection.onFinished callback
        } else if (skipAnimation) {
            stage = 3
            onReadyToShowNav()
        }
    }

    val mono = rememberJetBrainsMono()

    Column {

        // ── Stage 0 / 1: whoami ───────────────────────────────────────────────
        if (skipAnimation) {
            // Show everything instantly, no typewriter
            Text(
                text = "Om@Portfolio:~$ whoami",
                color = Color.Green,
                fontFamily = mono
            )
        } else {
            TypeWriterText(
                fullText = "whoami",
                showCursor = stage == 0
            )
        }

        Spacer(Modifier.height(8.dp))

        if (stage >= 1) {
            Text(
                text = "Hi, I'm Om Anand! Welcome to my portfolio...",
                color = Color.White,
                fontFamily = mono
            )
            Spacer(Modifier.height(8.dp))
        }

        // ── Stage 2 / 3: fastfetch ────────────────────────────────────────────
        if (stage >= 2) {
            if (skipAnimation) {
                Text(
                    text = "Om@Portfolio:~$ fastfetch",
                    color = Color.Green,
                    fontFamily = mono
                )
            } else {
                TypeWriterText(
                    fullText = "fastfetch",
                    showCursor = stage == 2
                )
            }
            Spacer(Modifier.height(8.dp))

            FastfetchSection(
                skipAnimation = skipAnimation,
                onFinished = {
                    stage = 3
                    onReadyToShowNav()
                }
            )
        }

        // ── Stage 3: Explore my resume ─────────────────────────────────────
        if (stage >= 3) {
            TypeWriterText(
                fullText = "Explore my Portfolio",
                showCursor = true,
                skipAnimation = skipAnimation
            )
        }
    }
}