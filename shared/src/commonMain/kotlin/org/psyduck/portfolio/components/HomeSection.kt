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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import om_portfolio.shared.generated.resources.Res
import om_portfolio.shared.generated.resources.om_avatar
import org.jetbrains.compose.resources.painterResource
import org.psyduck.portfolio.models.ProjectInfo
import org.psyduck.portfolio.models.projects
import org.psyduck.portfolio.theme.PortfolioColors.Blue400
import org.psyduck.portfolio.theme.PortfolioColors.Border
import org.psyduck.portfolio.theme.PortfolioColors.Pink500
import org.psyduck.portfolio.theme.PortfolioColors.Purple500
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.PortfolioColors.TextPrimary
import org.psyduck.portfolio.theme.PortfolioColors.TextSecondary

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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W800,
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
                    fontSize = 14.sp,
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
            fontSize = 13.sp,
            color = TextMuted,
            lineHeight = 20.sp
        )

        Spacer(Modifier.height(48.dp))

        // ── Project Cards Slider ─────────────────────────────────────────────
        val scrollState = rememberScrollState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading spacer for centering visual padding
            Spacer(Modifier.width(24.dp))

            projects.forEachIndexed { index, project ->
                val isFocused = index == selectedProjectIndex
                val scale by animateFloatAsState(
                    targetValue = if (isFocused) 1f else 0.88f,
                    animationSpec = tween(300),
                    label = "cardScale"
                )
                val alpha by animateFloatAsState(
                    targetValue = if (isFocused) 1f else 0.5f,
                    animationSpec = tween(300),
                    label = "cardAlpha"
                )

                ProjectCard(
                    project = project,
                    scale = scale,
                    alpha = alpha,
                    onClick = { selectedProjectIndex = index }
                )
            }

            // Trailing spacer
            Spacer(Modifier.width(60.dp))
        }
        
        Spacer(Modifier.height(48.dp))
    }
}

// ── Project Card ─────────────────────────────────────────────────────────────

@Composable
private fun ProjectCard(
    project: ProjectInfo,
    scale: Float,
    alpha: Float,
    onClick: () -> Unit
) {
    val cardShape = RoundedCornerShape(16.dp)

    Column(
        modifier = Modifier
            .scale(scale)
            .width(360.dp)
            .clip(cardShape)
            .background(Color(0xFF1E2128).copy(alpha = alpha))
            .border(1.dp, Color.White.copy(alpha = 0.08f * alpha), cardShape)
            .clickable { onClick() }
    ) {
        // Top section: Gradient cover
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    Brush.linearGradient(
                        listOf(
                            Purple500.copy(alpha = 0.8f * alpha),
                            Blue400.copy(alpha = 0.6f * alpha),
                            Pink500.copy(alpha = 0.7f * alpha)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = project.name.first().toString(),
                fontSize = 64.sp,
                fontWeight = FontWeight.W800,
                color = Color.White.copy(alpha = 0.7f * alpha)
            )
        }

        // Bottom section: Content
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            // Status row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.size(8.dp).background(project.status.color.copy(alpha = alpha), CircleShape))
                Text(
                    text = project.status.label,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W600,
                    color = project.status.color.copy(alpha = alpha)
                )
            }

            Spacer(Modifier.height(14.dp))

            // Title
            Text(
                text = project.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.W700,
                color = TextPrimary.copy(alpha = alpha),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(10.dp))

            // Description
            Text(
                text = project.description,
                fontSize = 14.sp,
                color = TextSecondary.copy(alpha = alpha),
                lineHeight = 22.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.height(72.dp) // Fixed height to keep cards uniform
            )

            Spacer(Modifier.height(24.dp))

            // Buttons row
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // More button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Purple500.copy(alpha = 0.25f * alpha),
                                    Blue400.copy(alpha = 0.20f * alpha)
                                )
                            )
                        )
                        .border(1.dp, Purple500.copy(alpha = 0.4f * alpha), RoundedCornerShape(8.dp))
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "More",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = TextPrimary.copy(alpha = alpha)
                    )
                }

                // GitHub button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.08f * alpha))
                        .border(1.dp, Color.White.copy(alpha = 0.2f * alpha), RoundedCornerShape(8.dp))
                        .clickable(enabled = alpha == 1f) {
                            kotlinx.browser.window.open(project.githubUrl, "_blank")
                        }
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "GitHub",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = TextPrimary.copy(alpha = alpha)
                    )
                }
            }
        }
    }
}
