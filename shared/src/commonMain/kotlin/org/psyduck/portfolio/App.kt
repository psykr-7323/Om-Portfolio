package org.psyduck.portfolio

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.psyduck.portfolio.components.AnimatedBackground
import org.psyduck.portfolio.components.HomeSection
import org.psyduck.portfolio.components.NavBar
import org.psyduck.portfolio.components.TerminalSection
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.rememberJetBrainsMono

@Composable
fun App() {
    MaterialTheme {
        val scrollState = rememberScrollState()

        // Show NavBar when user has scrolled past ~400px of the first viewport
        val showNav by remember {
            derivedStateOf { scrollState.value > 400 }
        }

        Box(Modifier.fillMaxSize()) {
            // Animated background covers everything
            AnimatedBackground()

            // Main scrollable content
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val screenHeight = maxHeight

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    // ── Section 1: Terminal (exactly 1 viewport height) ──────────
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight)
                    ) {
                        TerminalSection(
                            modifier = Modifier.align(Alignment.Center)
                        )

                        // Blinking scroll hint outside the terminal at bottom center
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

                        Text(
                            text = "↓ Scroll to explore ↓",
                            color = TextMuted.copy(alpha = blinkAlpha),
                            fontFamily = rememberJetBrainsMono(),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 32.dp)
                        )
                    }

                    // ── Section 2: Home ──────────────────────────────────────────
                    HomeSection(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // ── NavBar (top-right, appears on scroll) ────────────────────────
            NavBar(
                visible = showNav,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 24.dp)
            )
        }
    }
}