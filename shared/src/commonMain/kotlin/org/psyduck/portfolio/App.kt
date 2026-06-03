package org.psyduck.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.psyduck.portfolio.components.AnimatedBackground
import org.psyduck.portfolio.components.Terminal
import org.psyduck.portfolio.components.TypeWriterText

@Composable
fun App() {
    MaterialTheme {
        Box(
            Modifier.fillMaxSize()
        ) {
            AnimatedBackground()
            Terminal()
        }
    }
}