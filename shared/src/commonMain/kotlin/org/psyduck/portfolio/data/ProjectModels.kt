package org.psyduck.portfolio.data

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

enum class ProjectStatus(val label: String, val color: Color) {
    ACTIVE("Active", Color(0xFF22C55E)),
    BUILDING("Building", Color(0xFF3B82F6)),
    DISCONTINUED("Discontinued", Color(0xFFEF4444))
}

data class ProjectTimelineStep(
    val title: String,
    val subtitle: String,
    val detail: String,
    val imageRes: DrawableResource? = null
)

data class ProjectInfo(
    val slug: String,
    val name: String,
    val description: String,
    val status: ProjectStatus,
    val githubUrl: String,
    val stack: List<String>,
    val thumbnailColors: List<Color>,
    val highlight: String,
    val timeline: List<ProjectTimelineStep>,
    val cardImageRes: DrawableResource? = null
)
