package org.psyduck.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.psyduck.portfolio.components.AnimatedBackground
import org.psyduck.portfolio.theme.PortfolioColors

@Composable
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PortfolioColors.Background)
        ) {
            AnimatedBackground()
        }
    }
}
