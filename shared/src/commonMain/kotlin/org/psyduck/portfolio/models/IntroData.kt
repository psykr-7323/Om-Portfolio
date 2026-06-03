package org.psyduck.portfolio.models

data class FastFetchInfo(
    val label: String,
    val value: String
)

val terminalInfo = listOf(

    FastFetchInfo(
        "Name",
        "Om Anand"
    ),

    FastFetchInfo(
        "Role",
        "Android & Backend Developer"
    ),

    FastFetchInfo(
        "Stack",
        "Kotlin • Jetpack Compose • Ktor"
    ),

    FastFetchInfo(
        "Projects",
        "ThunderChat • Graviton • eUdyaan"
    ),

    FastFetchInfo(
        "Research",
        "ML-Based DASH Adaptation in 5G"
    ),

    FastFetchInfo(
        "Education",
        "B.Tech CS — KIIT (CGPA 8.69)"
    )

)