package org.psyduck.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.psyduck.portfolio.components.AnimatedBackground


@Composable
fun App() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        AnimatedBackground()
    }
}