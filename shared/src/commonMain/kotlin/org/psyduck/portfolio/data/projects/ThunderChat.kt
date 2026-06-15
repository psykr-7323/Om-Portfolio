package org.psyduck.portfolio.data.projects

import androidx.compose.ui.graphics.Color
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.ProjectStatus
import org.psyduck.portfolio.data.ProjectTimelineStep

val thunderChat = ProjectInfo(
    slug = "thunderchat",
    name = "ThunderChat",
    description = "Production-grade real-time chat server built with Kotlin/Ktor, JWT + Google OAuth, WebSocket messaging, and Clean Architecture with PostgreSQL persistence.",
    status = ProjectStatus.ACTIVE,
    githubUrl = "https://github.com/psykr-7323/ThunderChat",
    stack = listOf("Kotlin", "Ktor", "WebSockets", "PostgreSQL"),
    thumbnailColors = listOf(
        Color(0xFF0E7490),
        Color(0xFF1D4ED8),
        Color(0xFF7C3AED)
    ),
    highlight = "Real-time Kotlin backend",
    timeline = listOf(
        ProjectTimelineStep(
            title = "Discover",
            subtitle = "Auth and delivery model",
            detail = "Mapped the chat flow, OAuth entry points, and message delivery guarantees."
        ),
        ProjectTimelineStep(
            title = "Architect",
            subtitle = "Clean layers",
            detail = "Separated domain, data, and transport concerns for a maintainable server."
        ),
        ProjectTimelineStep(
            title = "Build",
            subtitle = "WebSocket runtime",
            detail = "Implemented live messaging, persistence, and user presence handling."
        ),
        ProjectTimelineStep(
            title = "Ship",
            subtitle = "Deploy and harden",
            detail = "Prepared for production with structured config and auth-safe endpoints."
        )
    )
)
