package org.psyduck.portfolio.data.projects

import androidx.compose.ui.graphics.Color
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.ProjectStatus
import org.psyduck.portfolio.data.ProjectTimelineStep

val myPokedex = ProjectInfo(
    slug = "mypokedex",
    name = "MyPokedex",
    description = "Multi-tenant REST API with strict data isolation, BCrypt session auth, H2 persistence, and parameterized PATCH/DELETE endpoints.",
    status = ProjectStatus.DISCONTINUED,
    githubUrl = "https://github.com/psykr-7323/MyPokedex",
    stack = listOf("Java", "REST", "H2", "Auth"),
    thumbnailColors = listOf(
        Color(0xFFDC2626),
        Color(0xFFF97316),
        Color(0xFF7C3AED)
    ),
    highlight = "Backend API playground",
    timeline = listOf(
        ProjectTimelineStep(
            title = "Design",
            subtitle = "Tenant isolation",
            detail = "Planned a REST backend that kept tenant data separated by design."
        ),
        ProjectTimelineStep(
            title = "Auth",
            subtitle = "Session flow",
            detail = "Implemented BCrypt-backed session handling for authenticated access."
        ),
        ProjectTimelineStep(
            title = "CRUD",
            subtitle = "Flexible endpoints",
            detail = "Added PATCH and DELETE operations with predictable request behavior."
        ),
        ProjectTimelineStep(
            title = "Lessons",
            subtitle = "Archived project",
            detail = "The project remains a useful reference for backend structure and constraints."
        )
    )
)
