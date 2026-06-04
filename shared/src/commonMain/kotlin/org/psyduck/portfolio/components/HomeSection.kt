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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

// ── Contact Link Data ────────────────────────────────────────────────────────

private data class ContactLink(val label: String, val url: String, val color: Color)

private val contactLinks = listOf(
    ContactLink("GitHub", "https://github.com/psykr-7323", Color(0xFFE6EDF3)),
    ContactLink("Email", "mailto:omanand1208@gmail.com", Color(0xFF22C55E)),
    ContactLink("LinkedIn", "https://linkedin.com/in/om-anand", Color(0xFF0A66C2))
)

// ── Home Section ─────────────────────────────────────────────────────────────

@Composable
fun HomeSection(modifier: Modifier = Modifier) {
    var selectedProjectIndex by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll cards every 10 seconds, resets on manual interaction
    LaunchedEffect(selectedProjectIndex) {
        delay(10_000)
        selectedProjectIndex = (selectedProjectIndex + 1) % projects.size
    }

    // Programmatically scroll the row to follow the selected card
    LaunchedEffect(selectedProjectIndex) {
        val cardWidthPx = 384 // ~360.dp card + 24.dp spacing in pixels (approximate)
        val targetScroll = (selectedProjectIndex * cardWidthPx).coerceAtLeast(0)
        scrollState.animateScrollTo(targetScroll)
    }

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

        Spacer(Modifier.height(24.dp))

        // ── Contact Links ────────────────────────────────────────────────────
        Text(
            text = "You can find me on",
            fontSize = 13.sp,
            color = TextMuted
        )

        Spacer(Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            contactLinks.forEach { link ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(link.color.copy(alpha = 0.12f))
                        .border(1.dp, link.color.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
                        .clickable {
                            kotlinx.browser.window.open(link.url, "_blank")
                        }
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "${link.label} ↗",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W600,
                        color = link.color
                    )
                }
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

        // ── "Projects I Build" Heading ───────────────────────────────────────
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Border)
            )
            Text(
                text = "  Projects I Build  ",
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = TextPrimary
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Border)
            )
        }

        Spacer(Modifier.height(32.dp))

        // ── Project Cards Slider with Arrows ─────────────────────────────────
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Card slider Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
                    .padding(horizontal = 40.dp), // leave room for arrows
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Leading spacer
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
                Spacer(Modifier.width(24.dp))
            }

            // Left arrow
            val canGoLeft = selectedProjectIndex > 0
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1E2128).copy(alpha = if (canGoLeft) 0.9f else 0.3f))
                    .border(1.dp, Color.White.copy(alpha = if (canGoLeft) 0.2f else 0.06f), CircleShape)
                    .clickable(enabled = canGoLeft) {
                        selectedProjectIndex = (selectedProjectIndex - 1).coerceAtLeast(0)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "◀",
                    fontSize = 18.sp,
                    color = if (canGoLeft) TextPrimary else TextMuted.copy(alpha = 0.3f)
                )
            }

            // Right arrow
            val canGoRight = selectedProjectIndex < projects.lastIndex
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1E2128).copy(alpha = if (canGoRight) 0.9f else 0.3f))
                    .border(1.dp, Color.White.copy(alpha = if (canGoRight) 0.2f else 0.06f), CircleShape)
                    .clickable(enabled = canGoRight) {
                        selectedProjectIndex = (selectedProjectIndex + 1).coerceAtMost(projects.lastIndex)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "▶",
                    fontSize = 18.sp,
                    color = if (canGoRight) TextPrimary else TextMuted.copy(alpha = 0.3f)
                )
            }
        }
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
