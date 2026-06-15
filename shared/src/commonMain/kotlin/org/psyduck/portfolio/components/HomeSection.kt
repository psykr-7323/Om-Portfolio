package org.psyduck.portfolio.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.browser.window
import om_portfolio.shared.generated.resources.Res
import om_portfolio.shared.generated.resources.om_avatar
import org.jetbrains.compose.resources.painterResource
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.contactLinks
import org.psyduck.portfolio.data.hashtags
import org.psyduck.portfolio.data.profileBio
import org.psyduck.portfolio.data.profileTagline
import org.psyduck.portfolio.data.skillBadges
import org.psyduck.portfolio.data.projects.allProjects
import org.psyduck.portfolio.theme.PortfolioColors.Purple500
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.PortfolioColors.TextPrimary
import org.psyduck.portfolio.theme.PortfolioColors.TextSecondary

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    onOpenProject: (ProjectInfo) -> Unit = {}
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isCompact = maxWidth < 600.dp
        val horizontalPadding = if (isCompact) 16.dp else 32.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding, vertical = 20.dp)
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFF0F1117))
                .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(28.dp))
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 22.dp)
            ) {
                // ── Avatar + Bio ────────────────────────────────────────────
                if (isCompact) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(96.dp)
                                .clip(CircleShape)
                                .border(2.dp, Purple500, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.om_avatar),
                                contentDescription = "Om Anand Avatar",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = profileTagline,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.W800,
                                color = TextPrimary,
                                lineHeight = 24.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )

                            Spacer(Modifier.height(10.dp))

                            Text(
                                text = profileBio,
                                fontSize = 13.sp,
                                color = TextSecondary,
                                lineHeight = 19.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .border(2.dp, Purple500, CircleShape)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.om_avatar),
                            contentDescription = "Om Anand Avatar",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(Modifier.width(20.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = profileTagline,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.W800,
                            color = TextPrimary,
                            lineHeight = 24.sp
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = profileBio,
                            fontSize = 13.sp,
                            color = TextSecondary,
                            lineHeight = 19.sp
                        )
                    }
                }
                }

                Spacer(Modifier.height(18.dp))

                // ── Contact links ───────────────────────────────────────────
                Text(
                    text = "You can find me on",
                    fontSize = 12.sp,
                    color = TextMuted
                )

                Spacer(Modifier.height(10.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    contactLinks.forEach { link ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(link.color.copy(alpha = 0.22f))
                                .clickable { window.open(link.url, "_blank") }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "${link.label} \u2197",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W600,
                                color = link.color
                            )
                        }
                    }
                }

                Spacer(Modifier.height(22.dp))

                // ── Skills title + badges with icons ────────────────────────
                Text(
                    text = "Skills I have...",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W800,
                    color = TextPrimary,
                    lineHeight = 26.sp
                )

                Spacer(Modifier.height(14.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    skillBadges.forEach { badge ->
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(badge.color.copy(alpha = 0.20f))
                                .padding(start = 8.dp, end = 14.dp, top = 7.dp, bottom = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(badge.iconRes),
                                contentDescription = badge.label,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                            Text(
                                text = badge.label,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W600,
                                color = badge.color
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // ── Hashtags ────────────────────────────────────────────────
                Text(
                    text = hashtags.joinToString("  "),
                    fontSize = 12.sp,
                    color = TextMuted,
                    lineHeight = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(20.dp))

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.White.copy(alpha = 0.06f)
                )

                Spacer(Modifier.height(22.dp))

                // ── Projects ────────────────────────────────────────────────
                ProjectShowcaseHeader()

                Spacer(Modifier.height(18.dp))

                ProjectCarousel(onOpenProject = onOpenProject)
            }
        }
    }
    }
}

@Composable
private fun ProjectShowcaseHeader() {
    Column {
        Text(
            text = "Projects I've built...",
            fontSize = 32.sp,
            fontWeight = FontWeight.W800,
            color = TextPrimary,
            lineHeight = 38.sp
        )
        Spacer(Modifier.height(6.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProjectCarousel(onOpenProject: (ProjectInfo) -> Unit) {
    val projects = allProjects
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = 0)

    Column(modifier = Modifier.fillMaxWidth()) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val containerWidth = maxWidth
            val cardWidth = when {
                containerWidth >= 1400.dp -> 280.dp
                containerWidth >= 1120.dp -> 264.dp
                containerWidth >= 820.dp -> 288.dp
                else -> containerWidth - 32.dp
            }

            LazyRow(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(projects.size) { index ->
                    val project = projects[index]

                    ProjectCard(
                        project = project,
                        isSelected = false,
                        cardWidth = cardWidth,
                        onSelect = { },
                        onOpenGitHub = { window.open(project.githubUrl, "_blank") },
                        onPreview = { onOpenProject(project) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(
    project: ProjectInfo,
    isSelected: Boolean,
    cardWidth: androidx.compose.ui.unit.Dp,
    onSelect: () -> Unit,
    onPreview: () -> Unit,
    onOpenGitHub: () -> Unit
) {
    val hoverSource = remember { MutableInteractionSource() }
    val hoverState by hoverSource.collectIsHoveredAsState()
    val elevated = hoverState || isSelected
    val alpha by animateFloatAsState(
        targetValue = if (elevated) 1f else 0.74f,
        animationSpec = tween(180),
        label = "projectCardAlpha"
    )

    Column(
            modifier = Modifier
                .width(cardWidth)
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFF17181D).copy(alpha = alpha))
            .border(
                width = 1.dp,
                color = if (isSelected) Color.White.copy(alpha = 0.28f) else Color.White.copy(alpha = 0.10f),
                shape = RoundedCornerShape(22.dp)
            )
            .hoverable(hoverSource)
            .clickable { onSelect() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(152.dp)
                .background(
                    Brush.linearGradient(
                        colors = project.thumbnailColors
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(Color.Black.copy(alpha = 0.24f))
                    .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(999.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = project.status.label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = project.highlight,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                    lineHeight = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(2.dp))

                Text(
                    text = project.slug.replace('-', ' ').uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.White.copy(alpha = 0.72f)
                )
            }

            Text(
                text = project.name.first().toString(),
                fontSize = 64.sp,
                fontWeight = FontWeight.W800,
                color = Color.White.copy(alpha = 0.16f),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 18.dp)
            )
        }

        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = project.name,
                fontSize = 19.sp,
                fontWeight = FontWeight.W700,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = project.description,
                fontSize = 13.sp,
                color = TextSecondary,
                lineHeight = 19.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.heightIn(min = 60.dp)
            )

            Spacer(Modifier.height(14.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ShowcaseButton(
                    label = "More",
                    accent = Color.White,
                    filled = true,
                    onClick = onPreview
                )

                ShowcaseButton(
                    label = "GitHub",
                    accent = TextPrimary,
                    filled = false,
                    onClick = onOpenGitHub
                )
            }
        }
    }
}

@Composable
private fun ShowcaseButton(
    label: String,
    accent: Color,
    filled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (filled) accent.copy(alpha = 0.95f) else Color.Transparent)
            .border(
                1.dp,
                if (filled) Color.Transparent else accent.copy(alpha = 0.18f),
                RoundedCornerShape(999.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.W700,
            color = if (filled) Color(0xFF111114) else accent
        )
    }
}
