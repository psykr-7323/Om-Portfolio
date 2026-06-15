package org.psyduck.portfolio.data.projects

import androidx.compose.ui.graphics.Color
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.ProjectStatus
import org.psyduck.portfolio.data.ProjectTimelineStep

val portfolio = ProjectInfo(
    slug = "portfolio",
    name = "Portfolio",
    description = "This website - Kotlin Compose Multiplatform compiled to WasmJS, deployed on Firebase Hosting with analytics. The site you're looking at right now!",
    status = ProjectStatus.BUILDING,
    githubUrl = "https://github.com/psykr-7323/Om-Portfolio",
    stack = listOf("Kotlin", "Compose", "Wasm", "Firebase"),
    thumbnailColors = listOf(
        Color(0xFF7C3AED),
        Color(0xFF2563EB),
        Color(0xFFF43F5E)
    ),
    highlight = "Compose Multiplatform site",
    timeline = listOf(
        ProjectTimelineStep(
            title = "Setup",
            subtitle = "App shell",
            detail = "Created the Wasm browser target and shared UI structure."
        ),
        ProjectTimelineStep(
            title = "Design",
            subtitle = "Terminal intro",
            detail = "Built the terminal-styled opening sequence and animated background."
        ),
        ProjectTimelineStep(
            title = "Showcase",
            subtitle = "Projects first",
            detail = "Added the homepage, project cards, and portfolio-specific branding."
        ),
        ProjectTimelineStep(
            title = "Deploy",
            subtitle = "Firebase hosting",
            detail = "Prepared the site for Firebase deployment and analytics integration."
        )
    )
)
