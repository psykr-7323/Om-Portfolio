package org.psyduck.portfolio.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.psyduck.portfolio.models.terminalInfo
import org.psyduck.portfolio.theme.PortfolioColors.Blue400
import org.psyduck.portfolio.theme.PortfolioColors.Border
import org.psyduck.portfolio.theme.PortfolioColors.Success
import org.psyduck.portfolio.theme.rememberJetBrainsMono

// Fires once every row has appeared so IntroSequence can advance to stage 3.
@Composable
fun FastfetchSection(
    skipAnimation: Boolean = false,
    onFinished: () -> Unit = {}
) {

    var visibleItems by remember { mutableStateOf(if (skipAnimation) terminalInfo.size else 0) }
    val mono = rememberJetBrainsMono()

    LaunchedEffect(skipAnimation) {
        if (skipAnimation) {
            visibleItems = terminalInfo.size
            onFinished()
        } else {
            terminalInfo.forEachIndexed { index, _ ->
                kotlinx.coroutines.delay(180)   // slightly faster stagger
                visibleItems = index + 1
            }
            onFinished()
        }
    }

    // ── Full-width info column (DotLogo removed) ─────────────────────────────
    Column(modifier = Modifier.fillMaxWidth()) {

        Spacer(Modifier.height(12.dp))

        // Staggered rows
        repeat(visibleItems) { index ->
            val item = terminalInfo[index]

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // KEY — Blue
                Text(
                    text = "${item.label}:",
                    color = Blue400,
                    fontFamily = mono,
                    modifier = Modifier.width(110.dp)
                )
                // VALUE — White
                Text(
                    text = item.value,
                    color = Color.White,
                    fontFamily = mono,
                    softWrap = true,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(10.dp))
        }

    }
}