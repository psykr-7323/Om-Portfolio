package org.psyduck.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.psyduck.portfolio.theme.PortfolioColors.Blue600
import org.psyduck.portfolio.theme.PortfolioColors.Pink600
import org.psyduck.portfolio.theme.PortfolioColors.Purple600
import org.psyduck.portfolio.theme.PortfolioColors.TextSecondary
import androidx.compose.ui.unit.sp

@Composable
fun TerminalSection(
    skipAnimation: Boolean = false,
    onFinished: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val terminalShape = RoundedCornerShape(18.dp)
    val scrollState = rememberScrollState()

    // Skip animations if user scrolls during the intro
    LaunchedEffect(scrollState.value) {
        if (scrollState.value > 0 && !skipAnimation) {
            onFinished()
        }
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val screenW = maxWidth
        val screenH = maxHeight
        val isCompact = screenW < 600.dp

        // ── Fixed-width terminal (like a real terminal window) ───────────
        // On desktop/laptop: a generous fixed width that looks like a proper terminal
        // On mobile: near-full width
        val terminalWidth = when {
            isCompact          -> screenW * 0.94f   // phone: near-full width
            screenW >= 1200.dp -> 700.dp            // large desktop
            screenW >= 900.dp  -> 640.dp            // normal laptop
            else               -> screenW * 0.82f   // small tablet / narrow window
        }

        // Max height cap — only used to prevent overflow, terminal wraps content
        val terminalMaxHeight = screenH * 0.85f

        // Responsive internal spacing
        val contentPadding = when {
            isCompact          -> 14.dp
            screenW >= 1200.dp -> 24.dp
            else               -> 20.dp
        }
        val textSize = if (isCompact) 13.sp else 15.sp
        val titleBarHeight = if (isCompact) 38.dp else 42.dp

        // ── Terminal shell ───────────────────────────────────────────────
        // wrapContentHeight → height is purely content-driven
        // heightIn(max = ...) → scroll only if content exceeds cap
        Column(
            modifier = Modifier
                .width(terminalWidth)
                .wrapContentHeight()
                .heightIn(max = terminalMaxHeight)
                .clip(terminalShape)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF11131A).copy(alpha = 0.80f),
                            Color(0xFF090B11).copy(alpha = 0.74f)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.25f),
                    shape = terminalShape
                )
        ) {
            // ── Title bar ────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(titleBarHeight)
                    .background(Color.Black)
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Box(Modifier.size(12.dp).background(Purple600, CircleShape))
                    Box(Modifier.size(12.dp).background(Blue600, CircleShape))
                    Box(Modifier.size(12.dp).background(Pink600, CircleShape))
                }

                Text("~/Portfolio", color = TextSecondary, fontWeight = FontWeight.W800)
            }

            HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.25f))

            // ── Content area — wraps to content, scrollable if overflows ─
            androidx.compose.material3.ProvideTextStyle(
                value = androidx.compose.ui.text.TextStyle(fontSize = textSize)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f, fill = false)   // take only as much as content needs
                        .verticalScroll(scrollState)
                        .padding(contentPadding)
                ) {
                    IntroSequence(
                        skipAnimation = skipAnimation,
                        onFinished = onFinished
                    )
                }
            }
        }
    }
}
