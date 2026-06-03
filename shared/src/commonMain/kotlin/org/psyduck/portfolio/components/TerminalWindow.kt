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

@Composable
fun Terminal(
    modifier: Modifier = Modifier
) {
    val terminalShape = RoundedCornerShape(18.dp)
    var showNav by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        // ── Terminal card ─────────────────────────────────────────────────────
        // fillMaxHeight(0.88f) instead of a fixed 560dp:
        // this adapts to the screen so all content fits without scrolling
        // on typical desktop/tablet displays.
        Column(
            modifier = Modifier
                .fillMaxWidth(0.78f)
                .fillMaxHeight(0.88f)           // adaptive — no more hard 560dp ceiling
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
            // Title bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(Color.Black)
                    .padding(4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .background(Color.Black)
                ) {
                    Box(Modifier.size(12.dp).background(Purple600, CircleShape))
                    Box(Modifier.size(12.dp).background(Blue600, CircleShape))
                    Box(Modifier.size(12.dp).background(Pink600, CircleShape))
                    Spacer(Modifier.width(400.dp))
                    Text("~/Portfolio", color = TextSecondary, fontWeight = FontWeight.W800)
                }
            }

            HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.25f))

            // Content — verticalScroll kept as fallback on very small displays,
            // but on normal desktop it will fit without scrolling.
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(22.dp)
            ) {
                IntroSequence(
                    onReadyToShowNav = { showNav = true }
                )
            }
        }

        // ── NavBar (top-right, slides in after Explore more!) ─────────────────
        NavBar(
            visible = showNav,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 24.dp)
        )
    }
}