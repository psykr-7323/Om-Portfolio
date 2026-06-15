package org.psyduck.portfolio.data

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import om_portfolio.shared.generated.resources.*

// ─── Profile content ────────────────────────────────────────────────────────
// Edit this file to update the About section on the homepage.
// Adding/removing items here automatically updates the UI.

val profileTagline = "Kotlin-first. Android-native. Backend-ready."

val profileBio = "I'm Om Anand - a Computer Science student at KIIT, Bhubaneswar, " +
        "building production-grade Android apps and backend systems with Kotlin.\n\n" +
        "I care about clean architecture, real-time systems, and solving " +
        "problems that matter - from mental wellness to 5G research."

// ─── Skill badges (with tech icons) ─────────────────────────────────────────

data class SkillBadge(
    val label: String,
    val color: Color,
    val iconRes: DrawableResource
)

val skillBadges = listOf(
    SkillBadge("Kotlin", Color(0xFF7F52FF), Res.drawable.icon_kotlin),
    SkillBadge("Ktor", Color(0xFFE97627), Res.drawable.icon_ktor),
    SkillBadge("Compose", Color(0xFF4DB33D), Res.drawable.icon_compose),
    SkillBadge("ML", Color(0xFF3B82F6), Res.drawable.icon_ml),
    SkillBadge("Backend", Color(0xFFF59E0B), Res.drawable.icon_backend),
    SkillBadge("Android", Color(0xFF3DDC84), Res.drawable.icon_android),
    SkillBadge("Firebase", Color(0xFFFFCA28), Res.drawable.icon_firebase),
    SkillBadge("Python", Color(0xFF3776AB), Res.drawable.icon_python),
    SkillBadge("PostgreSQL", Color(0xFF336791), Res.drawable.icon_postgresql)
)

// ─── Contact links ──────────────────────────────────────────────────────────

data class ContactLink(
    val label: String,
    val url: String,
    val color: Color
)

val contactLinks = listOf(
    ContactLink("GitHub", "https://github.com/psykr-7323", Color(0xFFE6EDF3)),
    ContactLink("Email", "mailto:omanand1208@gmail.com", Color(0xFF22C55E)),
    ContactLink("LinkedIn", "https://www.linkedin.com/in/om-anand-8bb7242b2/", Color(0xFF0A66C2))
)
// ─── Hashtags ───────────────────────────────────────────────────────────────

val hashtags = listOf(
    "#kotlin", "#android", "#compose", "#ktor", "#backend",
    "#cleanarchitecture", "#websockets", "#5g", "#ml", "#jetpackcompose"
)
