package org.psyduck.portfolio.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.psyduck.portfolio.data.terminalInfo
import org.psyduck.portfolio.theme.PortfolioColors.Blue400
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
                kotlinx.coroutines.delay(180)
                visibleItems = index + 1
            }
            onFinished()
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val isCompact = maxWidth < 400.dp
        val labelWidth = if (isCompact) 84.dp else 110.dp

        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.height(6.dp))

        repeat(visibleItems) { index ->
            val item = terminalInfo[index]

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "${item.label}:",
                    color = Blue400,
                    fontFamily = mono,
                    modifier = Modifier.width(labelWidth)
                )
                Text(
                    text = item.value,
                    color = Color.White,
                    fontFamily = mono,
                    softWrap = true,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(if (isCompact) 4.dp else 8.dp))
        }

    }
}
}