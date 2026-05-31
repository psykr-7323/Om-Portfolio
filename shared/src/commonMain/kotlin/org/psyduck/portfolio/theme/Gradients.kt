package org.psyduck.portfolio.theme

import androidx.compose.ui.graphics.Brush

object PortfolioGradients {

val KotlinGradient = Brush.linearGradient(
    colors = listOf(
        PortfolioColors.Purple500,
        PortfolioColors.Blue500,
        PortfolioColors.Pink500
    )
)

val PurpleGradient = Brush.linearGradient(
    colors = listOf(
        PortfolioColors.Purple700,
        PortfolioColors.Purple400
    )
)

val BlueGradient = Brush.linearGradient(
    colors = listOf(
        PortfolioColors.Blue700,
        PortfolioColors.Blue400
    )
)

val PinkGradient = Brush.linearGradient(
    colors = listOf(
        PortfolioColors.Pink700,
        PortfolioColors.Pink400
    )
)

val TerminalBorderGradient = Brush.linearGradient(
    colors = listOf(
        PortfolioColors.Purple500,
        PortfolioColors.Blue500,
        PortfolioColors.Pink500
    )
)
}
