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
import org.psyduck.portfolio.theme.PortfolioColors.Border
import org.psyduck.portfolio.theme.PortfolioColors.Success
import org.psyduck.portfolio.theme.rememberJetBrainsMono
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun FastfetchSection() {

    var visibleItems by remember {
        mutableStateOf(0)
    }

    val mono = rememberJetBrainsMono()

    LaunchedEffect(Unit) {

        terminalInfo.forEach {

            delay(260.milliseconds)

            visibleItems++
        }
    }

    Row(

        modifier =
            Modifier
                .fillMaxWidth(),

        verticalAlignment =
            Alignment.Top

    ) {

        // LEFT LOGO

        Box(

            modifier =
                Modifier
                    .width(440.dp)

        ) {

            DotLogo()

        }

        Spacer(
            Modifier.width(12.dp)
        )

        // RIGHT INFO

        Column(

            modifier =
                Modifier
                    .weight(1f)

        ) {

            Text(

                text =
                    "Om@Portfolio",

                color =
                    Success,

                fontFamily =
                    mono

            )

            Spacer(
                Modifier.height(10.dp)
            )

            HorizontalDivider(

                modifier =
                    Modifier
                        .fillMaxWidth(),

                color =
                    Border

            )

            Spacer(
                Modifier.height(20.dp)
            )

            repeat(visibleItems) { index ->

                val item =
                    terminalInfo[index]

                Row(

                    modifier =
                        Modifier
                            .fillMaxWidth(),

                    verticalAlignment =
                        Alignment.Top

                ) {

                    Text(

                        text =
                            "${item.label}:",

                        color =
                            Success,

                        fontFamily =
                            mono,

                        modifier =
                            Modifier
                                .width(130.dp)

                    )

                    Text(

                        text =
                            item.value,

                        color =
                            Color.White,

                        fontFamily =
                            mono,

                        softWrap =
                            true,

                        overflow =
                            TextOverflow.Visible,

                        modifier =
                            Modifier
                                .weight(1f)

                    )

                }

                Spacer(
                    Modifier.height(16.dp)
                )
            }

            HorizontalDivider(

                modifier =
                    Modifier
                        .fillMaxWidth(),

                color =
                    Border

            )

            Spacer(
                Modifier.height(18.dp)
            )

            FastfetchColors()

        }

    }

}