package org.psyduck.portfolio.models

import androidx.compose.ui.graphics.Color

enum class ProjectStatus(val label: String, val color: Color) {
    ACTIVE("Active", Color(0xFF22C55E)),
    BUILDING("Building", Color(0xFF3B82F6)),
    DISCONTINUED("Discontinued", Color(0xFFEF4444))
}

data class ProjectInfo(
    val name: String,
    val description: String,
    val status: ProjectStatus,
    val githubUrl: String
)

val projects = listOf(
    ProjectInfo(
        name = "ThunderChat",
        description = "Production-grade real-time chat server built with Kotlin/Ktor, JWT + Google OAuth, WebSocket messaging, and Clean Architecture with PostgreSQL persistence.",
        status = ProjectStatus.ACTIVE,
        githubUrl = "https://github.com/psykr-7323/ThunderChat"
    ),
    ProjectInfo(
        name = "Graviton",
        description = "Zero-dependency browser arcade game with a procedural spiral tunnel, AI Director difficulty scaling, 60 FPS physics engine, and procedural Web Audio sound effects.",
        status = ProjectStatus.ACTIVE,
        githubUrl = "https://github.com/psykr-7323/Graviton"
    ),
    ProjectInfo(
        name = "eUdyaan",
        description = "Mental wellness Android app with mood tracking, AI chatbot, anonymous forum, and consultation booking — built privacy-first with Jetpack Compose.",
        status = ProjectStatus.ACTIVE,
        githubUrl = "https://github.com/psykr-7323/eUdyaan"
    ),
    ProjectInfo(
        name = "Portfolio",
        description = "This website — Kotlin Compose Multiplatform compiled to WasmJS, deployed on Firebase Hosting with analytics. The site you're looking at right now!",
        status = ProjectStatus.BUILDING,
        githubUrl = "https://github.com/psykr-7323/Om-Portfolio"
    ),
    ProjectInfo(
        name = "Flood Risk System",
        description = "ML-powered flood risk dashboard processing 1.1M+ records with MLP Neural Network classification, real-time Streamlit simulation, and automated alert system.",
        status = ProjectStatus.ACTIVE,
        githubUrl = "https://github.com/psykr-7323/FloodRisk"
    ),
    ProjectInfo(
        name = "MyPokedex",
        description = "Multi-tenant REST API with strict data isolation, BCrypt session auth, H2 persistence, and parameterized PATCH/DELETE endpoints.",
        status = ProjectStatus.DISCONTINUED,
        githubUrl = "https://github.com/psykr-7323/MyPokedex"
    )
)
