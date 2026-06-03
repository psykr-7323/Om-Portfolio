package org.psyduck.portfolio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.psyduck.portfolio.theme.PortfolioGradients.PinkGradientBright
import org.psyduck.portfolio.theme.PortfolioGradients.PurpleGradientBright
import org.psyduck.portfolio.theme.rememberJetBrainsMono

// ─── DotLogo ────────────────────────────────────────────────────────────────
//
// O: circular, 12 lines, max width 30 chars
// A: triangular peak → crossbar → spreading legs, 12 lines, max width 25 chars
//    (A is naturally narrower than O — that is correct for the letterform)
//
// Font: 13sp / lineHeight 16sp → 12 lines ≈ 192sp height
// This matches 6 fastfetch rows at (20sp text + 12dp spacer) each.

@OptIn(ExperimentalTextApi::class)
@Composable
fun DotLogo() {
    val mono = rememberJetBrainsMono()

    // 12 lines — O shape (circular, symmetric)
    val o = """
           ............
         ................
       ......        ......
      ....              ....
     ....                ....
    ....                  ....
    ....                  ....
     ....                ....
      ....              ....
       ......        ......
         ................
           ............
    """.trimIndent()

    // 12 lines — A shape (peak at top, crossbar in middle, legs spread at bottom)
    // Each line is correctly sized: peak narrow, crossbar solid, legs wide.
    val a = """
            ....
           ......
          ........
         ....  ....
        ....    ....
       ....      ....
      ................
     ..................
    ....            ....
   ....              ....
  ....                ....
 ....                  ....
    """.trimIndent()

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = o,
            style = TextStyle(
                brush = PinkGradientBright,
                fontFamily = mono,
                fontSize = 13.sp,
                lineHeight = 16.sp,
            )
        )

        Text(
            text = a,
            style = TextStyle(
                brush = PurpleGradientBright,
                fontFamily = mono,
                fontSize = 13.sp,
                lineHeight = 16.sp,
            )
        )
    }
}