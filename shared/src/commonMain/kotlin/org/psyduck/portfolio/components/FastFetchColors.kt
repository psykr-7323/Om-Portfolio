package org.psyduck.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.psyduck.portfolio.theme.PortfolioColors.Purple700
import org.psyduck.portfolio.theme.PortfolioColors.Purple500
import org.psyduck.portfolio.theme.PortfolioColors.Purple600
import org.psyduck.portfolio.theme.PortfolioColors.Blue700
import org.psyduck.portfolio.theme.PortfolioColors.Blue600
import org.psyduck.portfolio.theme.PortfolioColors.Blue500
import org.psyduck.portfolio.theme.PortfolioColors.Pink700
import org.psyduck.portfolio.theme.PortfolioColors.Pink600
import org.psyduck.portfolio.theme.PortfolioColors.Pink500

@Composable
fun FastfetchColors() {

    val colors = listOf(

        Purple700,
        Purple600,
        Purple500,

        Blue700,
        Blue600,
        Blue500,

        Pink700,
        Pink600,
        Pink500

    )

    Row(

        horizontalArrangement =
            Arrangement.spacedBy(
                6.dp
            )

    ) {

        colors.forEach {

            Box(

                modifier =
                    Modifier
                        .size(
                            width = 26.dp,
                            height = 20.dp
                        )
                        .background(

                            color = it,

                            shape =
                                RoundedCornerShape(
                                    4.dp
                                )

                        )

            )

        }

    }

}