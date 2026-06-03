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
        "Android Developer"
    ),

    FastFetchInfo(
        "Backend",
        "Backend Developer"
    ),

    FastFetchInfo(
        "Data",
        "Data Analyst"
    ),

    FastFetchInfo(
        "Stack",
        "Kotlin • Compose"
    ),

    FastFetchInfo(
        "Database",
        "PostgreSQL • H2"
    ),

    FastFetchInfo(
        "Projects",
        "ThunderChat • Graviton"
    ),

    FastFetchInfo(
        "Research",
        "ML + DASH + 5G"

    )

)