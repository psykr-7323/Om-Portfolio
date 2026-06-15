package org.psyduck.portfolio.data.projects

import androidx.compose.ui.graphics.Color
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.ProjectStatus
import org.psyduck.portfolio.data.ProjectTimelineStep

val eUdyaan = ProjectInfo(
    slug = "eudyaan",
    name = "eUdyaan",
    description = "Mental wellness Android app with mood tracking, AI chatbot, anonymous forum, and consultation booking - built privacy-first with Jetpack Compose.",
    status = ProjectStatus.ACTIVE,
    githubUrl = "https://github.com/psykr-7323/eUdyaan",
    stack = listOf("Android", "Compose", "Firebase", "AI"),
    thumbnailColors = listOf(
        Color(0xFF14B8A6),
        Color(0xFF0EA5E9),
        Color(0xFF6366F1)
    ),
    highlight = "Privacy-first health app",
    timeline = listOf(
        ProjectTimelineStep(
            title = "Research",
            subtitle = "User needs",
            detail = "Focused on safe, low-friction interactions for mental wellness support."
        ),
        ProjectTimelineStep(
            title = "Experience",
            subtitle = "Compose-first UI",
            detail = "Designed calm mobile screens for mood logging, conversations, and forums."
        ),
        ProjectTimelineStep(
            title = "Integrate",
            subtitle = "AI and booking",
            detail = "Connected chatbot and consultation flows without sacrificing privacy cues."
        ),
        ProjectTimelineStep(
            title = "Polish",
            subtitle = "Mobile reliability",
            detail = "Refined the app for trustworthy daily use and clear user guidance."
        )
    )
)
