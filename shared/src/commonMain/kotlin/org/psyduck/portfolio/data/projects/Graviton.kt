package org.psyduck.portfolio.data.projects

import androidx.compose.ui.graphics.Color
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.ProjectStatus
import org.psyduck.portfolio.data.ProjectTimelineStep

val graviton = ProjectInfo(
    slug = "graviton",
    name = "Graviton",
    description = "Zero-dependency browser arcade game with a procedural spiral tunnel, AI Director difficulty scaling, 60 FPS physics engine, and procedural Web Audio sound effects.",
    status = ProjectStatus.ACTIVE,
    githubUrl = "https://github.com/psykr-7323/Graviton",
    stack = listOf("Kotlin", "Wasm", "Canvas", "Web Audio"),
    thumbnailColors = listOf(
        Color(0xFF3B82F6),
        Color(0xFF7C3AED),
        Color(0xFFE11D48)
    ),
    highlight = "Fast browser game loop",
    timeline = listOf(
        ProjectTimelineStep(
            title = "Concept",
            subtitle = "Arcade feel first",
            detail = "Defined a minimal browser game with immediate feedback and a compact control loop."
        ),
        ProjectTimelineStep(
            title = "Systems",
            subtitle = "Physics and AI",
            detail = "Built the tunnel motion, scaling difficulty, and movement logic."
        ),
        ProjectTimelineStep(
            title = "Audio",
            subtitle = "Procedural cues",
            detail = "Added Web Audio effects to make the browser game feel responsive and alive."
        ),
        ProjectTimelineStep(
            title = "Refine",
            subtitle = "Performance tuning",
            detail = "Focused on smooth frame pacing and predictable interaction across browsers."
        )
    )
)
