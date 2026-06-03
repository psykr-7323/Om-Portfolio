package org.psyduck.portfolio.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import om_portfolio.shared.generated.resources.Res
import om_portfolio.shared.generated.resources.om_avatar
import org.jetbrains.compose.resources.painterResource
import org.psyduck.portfolio.models.ProjectStatus
import org.psyduck.portfolio.models.projects
import org.psyduck.portfolio.theme.PortfolioColors
import org.psyduck.portfolio.theme.PortfolioColors.Blue400
import org.psyduck.portfolio.theme.PortfolioColors.Border
import org.psyduck.portfolio.theme.PortfolioColors.Pink500
import org.psyduck.portfolio.theme.PortfolioColors.Purple500
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.PortfolioColors.TextPrimary
import org.psyduck.portfolio.theme.PortfolioColors.TextSecondary
import org.psyduck.portfolio.theme.rememberJetBrainsMono

// ── Skill Badge Data ─────────────────────────────────────────────────────────

private data class SkillBadge(val label: String, val color: Color)

private val skillBadges = listOf(
    SkillBadge("Kotlin", Color(0xFF7F52FF)),      // Kotlin brand purple
    SkillBadge("Ktor", Color(0xFFE97627)),         // Ktor orange
    SkillBadge("Compose", Color(0xFF4DB33D)),       // Compose green
    SkillBadge("ML", Color(0xFF3B82F6)),           // Blue
    SkillBadge("Backend", Color(0xFFF59E0B)),       // Amber
    SkillBadge("Android", Color(0xFF3DDC84)),       // Android green
    SkillBadge("Firebase", Color(0xFFFFCA28)),       // Firebase yellow
    SkillBadge("Python", Color(0xFF3776AB)),        // Python blue
    SkillBadge("PostgreSQL", Color(0xFF336791))      // PostgreSQL slate
)

private val hashtags = listOf(
    "#kotlin", "#android", "#compose", "#ktor", "#backend",
    "#cleanarchitecture", "#websockets", "#5g", "#ml", "#jetpackcompose"
)

// ── Home Section ─────────────────────────────────────────────────────────────

@Composable
fun HomeSection(modifier: Modifier = Modifier) {
    val mono = rememberJetBrainsMono()
    var selectedProjectIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 32.dp)
    ) {

        // ── Avatar + Intro ───────────────────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Circular avatar
            Image(
                painter = painterResource(Res.drawable.om_avatar),
                contentDescription = "Om Anand Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, Purple500, CircleShape)
            )

            Spacer(Modifier.width(28.dp))

            // Intro text
            Column(modifier = Modifier.weight(1f)) {
                // Tagline
                Text(
                    text = "Kotlin-first. Android-native. Backend-ready.",
                    fontFamily = mono,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = TextPrimary,
                    lineHeight = 28.sp
                )

                Spacer(Modifier.height(14.dp))

                // Brief intro
                Text(
                    text = "I'm Om Anand — a Computer Science student at KIIT, Bhubaneswar, " +
                            "building production-grade Android apps and backend systems with Kotlin.\n\n" +
                            "I care about clean architecture, real-time systems, and solving " +
                            "problems that matter — from mental wellness to 5G research.",
                    fontFamily = mono,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // ── Skill Badges ─────────────────────────────────────────────────────
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            skillBadges.forEach { badge ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(badge.color.copy(alpha = 0.15f))
                        .border(1.dp, badge.color.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = badge.label,
                        fontFamily = mono,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        color = badge.color
                    )
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        // ── Hashtags ─────────────────────────────────────────────────────────
        Text(
            text = hashtags.joinToString("  "),
            fontFamily = mono,
            fontSize = 12.sp,
            color = TextMuted,
            lineHeight = 20.sp
        )

        Spacer(Modifier.height(40.dp))

        // ── Project Cards Slider ─────────────────────────────────────────────
        val scrollState = rememberScrollState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading spacer for centering
            Spacer(Modifier.width(60.dp))

            projects.forEachIndexed { index, project ->
                val isFocused = index == selectedProjectIndex
                val scale by animateFloatAsState(
                    targetValue = if (isFocused) 1f else 0.78f,
                    animationSpec = tween(300),
                    label = "cardScale"
                )
                val alpha by animateFloatAsState(
                    targetValue = if (isFocused) 1f else 0.6f,
                    animationSpec = tween(300),
                    label = "cardAlpha"
                )

                ProjectCard(
                    name = project.name,
                    status = project.status,
                    isFocused = isFocused,
                    scale = scale,
                    alpha = alpha,
                    onClick = { selectedProjectIndex = index }
                )
            }

            // Trailing spacer
            Spacer(Modifier.width(60.dp))
        }

        Spacer(Modifier.height(24.dp))

        // ── Project Info Box ─────────────────────────────────────────────────
        val selectedProject = projects[selectedProjectIndex]

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF11131A).copy(alpha = 0.70f),
                            Color(0xFF090B11).copy(alpha = 0.60f)
                        )
                    )
                )
                .border(1.dp, Border, RoundedCornerShape(14.dp))
                .padding(24.dp)
        ) {
            // Project name
            Text(
                text = selectedProject.name,
                fontFamily = mono,
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
                color = TextPrimary
            )

            Spacer(Modifier.height(10.dp))

            // Description
            Text(
                text = selectedProject.description,
                fontFamily = mono,
                fontSize = 13.sp,
                color = TextSecondary,
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(20.dp))

            // Buttons row
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // More button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(Purple500.copy(alpha = 0.25f), Blue400.copy(alpha = 0.20f))
                            )
                        )
                        .border(1.dp, Purple500.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                        .clickable { /* Navigate to project detail page - future */ }
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "More",
                        fontFamily = mono,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W600,
                        color = TextPrimary
                    )
                }

                // GitHub button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.08f))
                        .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .clickable {
                            kotlinx.browser.window.open(selectedProject.githubUrl, "_blank")
                        }
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "GitHub",
                        fontFamily = mono,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W600,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}

// ── Project Card ─────────────────────────────────────────────────────────────

@Composable
private fun ProjectCard(
    name: String,
    status: ProjectStatus,
    isFocused: Boolean,
    scale: Float,
    alpha: Float,
    onClick: () -> Unit
) {
    val mono = rememberJetBrainsMono()
    val cardShape = RoundedCornerShape(14.dp)

    Column(
        modifier = Modifier
            .scale(scale)
            .width(if (isFocused) 200.dp else 200.dp)   // same base width, scale handles size
            .clip(cardShape)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF15172A).copy(alpha = alpha),
                        Color(0xFF0D0F1A).copy(alpha = alpha)
                    )
                )
            )
            .border(
                width = if (isFocused) 1.5.dp else 1.dp,
                color = if (isFocused) Purple500.copy(alpha = 0.6f)
                else Color.White.copy(alpha = 0.12f),
                shape = cardShape
            )
            .clickable { onClick() }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder image area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            Purple500.copy(alpha = 0.12f),
                            Blue400.copy(alpha = 0.08f),
                            Pink500.copy(alpha = 0.10f)
                        )
                    )
                )
                .border(1.dp, Border, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.first().toString(),
                fontFamily = mono,
                fontSize = 36.sp,
                fontWeight = FontWeight.W700,
                color = Color.White.copy(alpha = 0.3f)
            )
        }

        Spacer(Modifier.height(12.dp))

        // Project name
        Text(
            text = name,
            fontFamily = mono,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = Color.White.copy(alpha = alpha),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(6.dp))

        // Status dot + label
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(status.color, CircleShape)
            )
            Text(
                text = status.label,
                fontFamily = mono,
                fontSize = 11.sp,
                color = status.color.copy(alpha = alpha)
            )
        }
    }
}
