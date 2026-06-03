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
import org.psyduck.portfolio.theme.PortfolioGradients.PinkGradient
import org.psyduck.portfolio.theme.PortfolioGradients.PurpleGradient
import org.psyduck.portfolio.theme.rememberJetBrainsMono

@OptIn(ExperimentalTextApi::class)
@Composable
fun DotLogo() {
    val mono = rememberJetBrainsMono()

    // 12 lines high, wider curves
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

    // 12 lines high, matches the weight of the 'O'
    val a = """
               ......
              ........
             ..........
            ....    ....
           ....      ....
          ....        ....
         ..................
        ....................
       ....              ....
      ....                ....
     ....                  ....
    ....                    ....
    """.trimIndent()

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Increased spacing between letters
        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = o,
            style = TextStyle(
                brush = PinkGradient,
                fontFamily = mono,
                fontSize = 10.sp,     // Bumped up slightly for clarity
                lineHeight = 12.sp,   // Important: Line height > font size prevents vertical squishing
                letterSpacing = 1.sp  // Gives the dots some horizontal breathing room
            )
        )

        Text(
            text = a,
            style = TextStyle(
                brush = PurpleGradient,
                fontFamily = mono,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                letterSpacing = 1.sp
            )
        )
    }
}