package org.psyduck.portfolio.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import om_portfolio.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font
import om_portfolio.shared.generated.resources.*

@Composable
fun rememberJetBrainsMono(): FontFamily {
    return FontFamily(
        Font(resource = Res.font.JetBrainsMono_Regular),
        Font(resource = Res.font.JetBrainsMono_Medium),
        Font(resource = Res.font.JetBrainsMono_Bold)
    )
}