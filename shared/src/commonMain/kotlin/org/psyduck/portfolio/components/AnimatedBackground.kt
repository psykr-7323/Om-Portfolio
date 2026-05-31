package org.psyduck.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.psyduck.portfolio.theme.PortfolioColors

@Composable
fun AnimatedBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PortfolioColors.Background)
    )
}
