package org.psyduck.portfolio.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.withFrameMillis
import kotlinx.coroutines.isActive
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

// ─── Data Models ────────────────────────────────────────────────────────────

private data class GridDot(
    val col: Int,
    val row: Int,
    val phaseOffset: Float,  // Where in the fade-in/out cycle the dot starts
    val twinkleSpeed: Float, // How fast this specific dot pulses
    val maxAlpha: Float      // Random maximum brightness (some dots stay dim)
)

// ─── Tuning Constants ────────────────────────────────────────────────────────

private const val GRID_SPACING    = 34f
private const val DOT_RADIUS      = 2.2f

private const val GRID_COLS       = 60
private const val GRID_ROWS       = 40

// ─── Composable ──────────────────────────────────────────────────────────────

@Composable
fun AnimatedBackground(modifier: Modifier = Modifier) {

    // Used to continuously drive the twinkling effect in the Canvas
    var currentTimeMs by remember { mutableStateOf(0L) }

    // ── 1. Build the Grid ────────────────────────────────────────────────────
    val dots: List<GridDot> = remember {
        buildList {
            val rng = Random(1337)

            for (row in 0 until GRID_ROWS) {
                for (col in 0 until GRID_COLS) {

                    // 75% chance to spawn a dot.
                    // This creates random dense regions and random empty voids.
                    if (rng.nextFloat() < 0.75f) {
                        add(
                            GridDot(
                                col = col,
                                row = row,
                                phaseOffset = rng.nextFloat() * (2f * PI.toFloat()),
                                twinkleSpeed = rng.nextFloat() * 1.5f + 0.5f,
                                // Give each dot a random max brightness between 20% and 100%
                                maxAlpha = rng.nextFloat() * 0.8f + 0.2f
                            )
                        )
                    }
                }
            }
        }
    }

    // ── 2. Animation Loop ───────────────────────────────────────────────────
    LaunchedEffect(Unit) {
        while (isActive) {
            // This runs efficiently at 60fps and only triggers Canvas redraws
            currentTimeMs = withFrameMillis { it }
        }
    }

    // ── 3. Canvas Drawing ────────────────────────────────────────────────────
    Canvas(modifier = modifier.fillMaxSize()) {

        val timeSec = currentTimeMs / 1000f

        // Draw solid black background
        drawRect(color = Color.Black)

        // Calculate offsets to center the grid on the screen
        val offsetX = (size.width  - (GRID_COLS - 1) * GRID_SPACING) / 2f
        val offsetY = (size.height - (GRID_ROWS - 1) * GRID_SPACING) / 2f

        // Render Twinkling Dots
        dots.forEach { dot ->
            // Sine wave between 0.0 and 1.0 based on time
            val rawPulse = (sin(timeSec * dot.twinkleSpeed + dot.phaseOffset) + 1f) / 2f

            // Cubing the value makes the dot stay dim/invisible for longer,
            // and glow up briefly, creating a much better "twinkle" effect.
            val pulseAlpha = rawPulse * rawPulse * rawPulse

            // Apply this dot's specific maximum brightness constraint
            val currentAlpha = pulseAlpha * dot.maxAlpha

            // Optimization: Only draw dots if they are actually visible enough to see
            if (currentAlpha > 0.03f) {
                drawCircle(
                    color  = Color.White.copy(alpha = currentAlpha),
                    radius = DOT_RADIUS,
                    center = Offset(
                        x = offsetX + (dot.col * GRID_SPACING),
                        y = offsetY + (dot.row * GRID_SPACING)
                    )
                )
            }
        }
    }
}