package org.psyduck.portfolio.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.browser.window
import org.jetbrains.compose.resources.painterResource
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.ProjectTimelineStep
import org.psyduck.portfolio.theme.PortfolioColors.TextMuted
import org.psyduck.portfolio.theme.PortfolioColors.TextPrimary
import org.psyduck.portfolio.theme.PortfolioColors.TextSecondary

@Composable
fun ProjectDetailPage(
    project: ProjectInfo,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = androidx.compose.foundation.lazy.rememberLazyListState()
    var activeStepIndex by remember(project) { androidx.compose.runtime.mutableStateOf(0) }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isCompact = maxWidth < 980.dp
        val horizontalPadding = if (isCompact) 16.dp else 32.dp

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                ProjectIntroShell(
                    project = project,
                    onBack = onBack
                )
            }

            item {
                TimelineStickyFrame(
                    project = project,
                    activeStepIndex = activeStepIndex,
                    onNodeClick = { index -> activeStepIndex = index }
                )
            }
        }
    }
}

@Composable
private fun ProjectIntroShell(
    project: ProjectInfo,
    onBack: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFF0F1117))
            .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(32.dp))
            .padding(28.dp)
    ) {
        val isCompact = maxWidth < 980.dp

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SurfaceLink(
                    label = "← Home",
                    filled = false,
                    accent = TextPrimary,
                    onClick = onBack
                )

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SurfaceLink(
                        label = "GitHub ↗",
                        filled = false,
                        accent = TextPrimary,
                        onClick = { window.open(project.githubUrl, "_blank") }
                    )
                    SurfaceLink(
                        label = project.status.label,
                        filled = true,
                        accent = project.status.color,
                        onClick = {}
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            if (isCompact) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    ProjectHeroPanel(
                        project = project,
                        modifier = Modifier.fillMaxWidth().height(280.dp)
                    )
                    ProjectInfoPanel(
                        project = project,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    ProjectHeroPanel(
                        project = project,
                        modifier = Modifier.weight(0.86f).fillMaxHeight()
                    )
                    ProjectInfoPanel(
                        project = project,
                        modifier = Modifier.weight(1f).fillMaxHeight()
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectHeroPanel(
    project: ProjectInfo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(Brush.linearGradient(project.thumbnailColors))
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(999.dp))
                .background(Color.Black.copy(alpha = 0.24f))
                .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(999.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = project.highlight,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.W700
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text(
                text = project.slug.replace('-', ' ').uppercase(),
                color = Color.White.copy(alpha = 0.68f),
                fontSize = 11.sp,
                fontWeight = FontWeight.W700
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = project.name,
                color = Color.White,
                fontSize = 30.sp,
                lineHeight = 34.sp,
                fontWeight = FontWeight.W800
            )
        }

        Text(
            text = project.name.first().toString(),
            color = Color.White.copy(alpha = 0.14f),
            fontSize = 120.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        )
    }
}

@Composable
private fun ProjectInfoPanel(
    project: ProjectInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF14171F))
            .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(28.dp))
            .padding(24.dp)
    ) {
        Text(
            text = project.name,
            color = TextPrimary,
            fontSize = 30.sp,
            lineHeight = 34.sp,
            fontWeight = FontWeight.W800
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = project.description,
            color = TextSecondary,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
        Spacer(Modifier.height(20.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            project.stack.forEach { tech ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color.White.copy(alpha = 0.08f))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = tech,
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SurfaceLink(
                label = "GitHub ↗",
                filled = false,
                accent = TextPrimary,
                onClick = { window.open(project.githubUrl, "_blank") }
            )
            SurfaceLink(
                label = project.status.label,
                filled = true,
                accent = project.status.color,
                onClick = {}
            )
        }

        Spacer(Modifier.height(22.dp))

        DetailStat(label = "Timeline steps", value = project.timeline.size.toString())
        Spacer(Modifier.height(14.dp))
        DetailStat(label = "Primary focus", value = project.highlight)
        Spacer(Modifier.height(14.dp))
        DetailStat(label = "Current status", value = project.status.label)
    }
}

@Composable
private fun DetailStat(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = TextMuted, fontSize = 12.sp)
        Spacer(Modifier.width(16.dp))
        Text(
            text = value,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.W700,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = 240.dp)
        )
    }
}

// ─── Timeline ───────────────────────────────────────────────────────────────

@Composable
private fun TimelineStickyFrame(
    project: ProjectInfo,
    activeStepIndex: Int,
    onNodeClick: (Int) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFF0F1117))
            .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(32.dp))
    ) {
        val compact = maxWidth < 980.dp

        Column(
            modifier = Modifier.padding(if (compact) 16.dp else 28.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            Column {
                Text(
                    text = "Project timeline",
                    color = TextPrimary,
                    fontSize = 30.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.W800
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Navigate through each phase of this project's journey.",
                    color = TextSecondary,
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    modifier = Modifier.widthIn(max = 760.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                HorizontalTimelineRail(
                    steps = project.timeline,
                    activeStepIndex = activeStepIndex,
                    onNodeClick = onNodeClick,
                    modifier = Modifier.fillMaxWidth()
                )

                AnimatedContent(
                    targetState = activeStepIndex,
                    transitionSpec = {
                        if (targetState > initialState) {
                            (fadeIn(tween(300)) + slideInHorizontally(tween(300)) { w -> w / 3 })
                                .togetherWith(fadeOut(tween(300)) + slideOutHorizontally(tween(300)) { w -> -w / 3 })
                        } else {
                            (fadeIn(tween(300)) + slideInHorizontally(tween(300)) { w -> -w / 3 })
                                .togetherWith(fadeOut(tween(300)) + slideOutHorizontally(tween(300)) { w -> w / 3 })
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { index ->
                    TimelineContentCard(
                        index = index,
                        step = project.timeline[index],
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Navigation buttons
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "PREVIOUS",
                        color = if (activeStepIndex > 0) Color(0xFFD7FF58) else TextMuted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = activeStepIndex > 0) { onNodeClick(activeStepIndex - 1) }
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                    Text(
                        text = "NEXT",
                        color = if (activeStepIndex < project.timeline.lastIndex) Color(0xFFD7FF58) else TextMuted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = activeStepIndex < project.timeline.lastIndex) { onNodeClick(activeStepIndex + 1) }
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun HorizontalTimelineRail(
    steps: List<ProjectTimelineStep>,
    activeStepIndex: Int,
    onNodeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, _ ->
            val isActive = index == activeStepIndex
            val isCompleted = index < activeStepIndex
            val nodeSize = 24.dp

            val nodeBgColor by animateColorAsState(
                if (isActive || isCompleted) Color(0xFFD7FF58) else Color.Transparent,
                label = "nodeBg"
            )
            val nodeBorderColor by animateColorAsState(
                if (isActive || isCompleted) Color(0xFFD7FF58) else Color.White.copy(alpha = 0.2f),
                label = "nodeBorder"
            )
            val lineColor by animateColorAsState(
                if (isCompleted) Color(0xFFD7FF58) else Color.White.copy(alpha = 0.1f),
                label = "line"
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = if (index != steps.lastIndex) Modifier.weight(1f) else Modifier
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onNodeClick(index) }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(nodeSize)
                            .clip(CircleShape)
                            .background(nodeBgColor)
                            .border(1.dp, nodeBorderColor, CircleShape)
                    )
                }

                if (index != steps.lastIndex) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .background(lineColor)
                    )
                }
            }
        }
    }
}

@Composable
private fun TimelineContentCard(
    index: Int,
    step: ProjectTimelineStep,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF14171F))
            .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(28.dp))
    ) {
        val isCompact = maxWidth < 600.dp

        Column(
            modifier = Modifier.padding(if (isCompact) 16.dp else 24.dp)
        ) {
            // ── Header row ──────────────────────────────────────────────
            if (isCompact) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column {
                        Text(
                            text = "STEP ${index + 1}",
                            color = Color(0xFFD7FF58),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = step.title,
                            color = TextPrimary,
                            fontSize = 24.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.W800
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(Color.White.copy(alpha = 0.06f))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = step.subtitle,
                            color = TextSecondary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = "STEP ${index + 1}",
                            color = Color(0xFFD7FF58),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.W700
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = step.title,
                            color = TextPrimary,
                            fontSize = 28.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight.W800
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(Color.White.copy(alpha = 0.06f))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = step.subtitle,
                            color = TextSecondary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // ── Content box — dynamic height ────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0xFF101319))
                    .border(1.dp, Color.White.copy(alpha = 0.04f), RoundedCornerShape(22.dp))
                    .padding(22.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "What changed here",
                        color = TextMuted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = step.detail,
                        color = TextPrimary,
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.widthIn(max = 620.dp)
                    )

                    // ── Optional step image ─────────────────────────────
                    step.imageRes?.let { imgRes ->
                        Spacer(Modifier.height(18.dp))
                        Image(
                            painter = painterResource(imgRes),
                            contentDescription = step.title,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SurfaceLink(
    label: String,
    filled: Boolean,
    accent: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (filled) accent.copy(alpha = 0.2f) else Color.Transparent)
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
            color = accent,
            fontSize = 13.sp,
            fontWeight = FontWeight.W700
        )
    }
}
