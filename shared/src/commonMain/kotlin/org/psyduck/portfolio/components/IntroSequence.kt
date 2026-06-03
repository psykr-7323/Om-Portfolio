package org.psyduck.portfolio.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.rememberJetBrainsMono
import kotlin.time.Duration.Companion.milliseconds

// ─── Stages ──────────────────────────────────────────────────────────────────
// 0 → "whoami" typewriter running, cursor blinking
// 1 → greeting appears
// 2 → "fastfetch" + FastfetchSection appear
// 3 → FastfetchSection done → scroll hint appears with blinking cursor
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

    // Blinking animation for the scroll hint
    val infiniteTransition = rememberInfiniteTransition(label = "blink")
    val blinkAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blinkAlpha"
    )

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

        // ── Stage 3: Blinking scroll hint ─────────────────────────────────────
        if (stage >= 3) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "↓ Scroll to explore ↓",
                color = TextMuted.copy(alpha = blinkAlpha),
                fontFamily = mono
            )
        }
    }
}