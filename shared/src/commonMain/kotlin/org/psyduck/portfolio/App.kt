package org.psyduck.portfolio

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.psyduck.portfolio.components.AnimatedBackground
import org.psyduck.portfolio.components.HomeSection
import org.psyduck.portfolio.components.NavBar
import org.psyduck.portfolio.components.TerminalSection
import org.psyduck.portfolio.theme.PortfolioColors.Background
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.rememberJetBrainsMono

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun App() {
    MaterialTheme {
        val listState = rememberLazyListState()

        // Show NavBar when user has scrolled down (index > 0 or offset > 400)
        val showNav by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 400 }
        }

        // Lock terminal animation so it doesn't replay on scroll back up
        var isTerminalAnimationDone by remember { mutableStateOf(false) }

        Box(Modifier.fillMaxSize()) {
            
            // Main scrollable content with snap fling behavior
            val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
            
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val screenHeight = maxHeight
                
                LazyColumn(
                    state = listState,
                    flingBehavior = flingBehavior,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // ── Section 1: Terminal (exactly 1 viewport height) ──────────
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(screenHeight)
                        ) {
                            // Animated background is ONLY shown in the terminal section now
                            AnimatedBackground()
                            
                            TerminalSection(
                                skipAnimation = isTerminalAnimationDone,
                                onFinished = { isTerminalAnimationDone = true },
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
                    }

                    // ── Section 2: Home ──────────────────────────────────────────
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = screenHeight) // Ensures it's AT LEAST full height
                                .background(Background) // Dark background without stars
                        ) {
                            HomeSection(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
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