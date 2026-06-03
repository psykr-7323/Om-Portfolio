package org.psyduck.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.psyduck.portfolio.components.AnimatedBackground
import org.psyduck.portfolio.components.HomeSection
import org.psyduck.portfolio.components.NavBar
import org.psyduck.portfolio.components.TerminalSection

@Composable
fun App() {
    MaterialTheme {
        val scrollState = rememberScrollState()

        // Show NavBar when user has scrolled past ~80% of the first viewport
        val showNav by remember {
            derivedStateOf { scrollState.value > 400 }
        }

        Box(Modifier.fillMaxSize()) {
            // Animated background covers everything
            AnimatedBackground()

            // Main scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // ── Section 1: Terminal (full viewport) ──────────────────────
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(900.dp)  // ~one viewport height
                ) {
                    TerminalSection()
                }

                // ── Section 2: Home ──────────────────────────────────────────
                HomeSection(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // ── NavBar (top-right, appears on scroll) ────────────────────────
            NavBar(
                visible = showNav,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 24.dp)
            )
        }
    }
}