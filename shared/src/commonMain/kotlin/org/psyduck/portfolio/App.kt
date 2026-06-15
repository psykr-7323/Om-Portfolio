package org.psyduck.portfolio

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.psyduck.portfolio.components.AnimatedBackground
import org.psyduck.portfolio.components.HomeSection
import org.psyduck.portfolio.components.ProjectDetailPage
import org.psyduck.portfolio.components.TerminalSection
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.rememberJetBrainsMono
import org.psyduck.portfolio.viewmodel.PortfolioViewModel

@Composable
fun App(viewModel: PortfolioViewModel = viewModel { PortfolioViewModel() }) {
    MaterialTheme {
        val listState = rememberLazyListState()

        Box(Modifier.fillMaxSize()) {
            AnimatedBackground()

            if (viewModel.selectedProject == null) {
                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    val screenHeight = maxHeight

                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(screenHeight)
                            ) {
                                TerminalSection(
                                    skipAnimation = viewModel.isTerminalAnimationDone,
                                    onFinished = { viewModel.onTerminalAnimationDone() },
                                    modifier = Modifier.align(Alignment.Center)
                                )

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

                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = screenHeight)
                            ) {
                                HomeSection(
                                    modifier = Modifier.fillMaxWidth(),
                                    onOpenProject = { viewModel.selectProject(it) }
                                )
                            }
                        }
                    }
                }
            } else {
                ProjectDetailPage(
                    project = viewModel.selectedProject!!,
                    onBack = { viewModel.goHome() },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
