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
import kotlinx.coroutines.delay
import org.psyduck.portfolio.models.terminalInfo
import org.psyduck.portfolio.theme.PortfolioColors.Blue400
import org.psyduck.portfolio.theme.PortfolioColors.Border
import org.psyduck.portfolio.theme.PortfolioColors.Success
import org.psyduck.portfolio.theme.rememberJetBrainsMono
import kotlin.time.Duration.Companion.milliseconds

// Fires once every row has appeared so IntroSequence can advance to stage 3.
@Composable
fun FastfetchSection(onFinished: () -> Unit = {}) {

    var visibleItems by remember { mutableStateOf(0) }
    val mono = rememberJetBrainsMono()

    LaunchedEffect(Unit) {
        terminalInfo.forEach {
            delay(220.milliseconds)   // slightly faster stagger to keep it snappy
            visibleItems++
        }
        onFinished()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        // ── LEFT: OA Dot Logo ────────────────────────────────────────────────
        Box(modifier = Modifier.weight(0.40f)) {
            DotLogo()
        }

        Spacer(Modifier.width(15.dp))

        // ── RIGHT: Info column ───────────────────────────────────────────────
        Column(modifier = Modifier.weight(0.60f)) {

            // Header
            Text(
                text = "Om@Portfolio",
                color = Success,
                fontFamily = mono
            )

            Spacer(Modifier.height(8.dp))

            HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Border)

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

                Spacer(Modifier.height(12.dp))   // tighter than 16dp → saves vertical space
            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Border)

            Spacer(Modifier.height(14.dp))

            FastfetchColors()
        }
    }
}