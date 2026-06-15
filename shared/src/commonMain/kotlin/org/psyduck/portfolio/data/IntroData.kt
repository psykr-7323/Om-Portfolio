package org.psyduck.portfolio.data

// ─── Terminal "fastfetch" info rows ─────────────────────────────────────────
// Edit this file to update the terminal intro section.

data class FastFetchInfo(
    val label: String,
    val value: String
)

val terminalInfo = listOf(
    FastFetchInfo("Role", "Android & Kotlin Developer"),
    FastFetchInfo("Stack", "Kotlin · Compose · Ktor · Python"),
    FastFetchInfo("Data / ML", "Pandas · NumPy · Scikit-learn · Streamlit · Excel · PowerBI · Tableau"),
    FastFetchInfo("Shell", "Android Studio · IntelliJ · Git · VS Code"),
    FastFetchInfo("Research", "ML-Based DASH in 5G Networks")
)
