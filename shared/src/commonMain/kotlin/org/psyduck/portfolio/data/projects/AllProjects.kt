package org.psyduck.portfolio.data.projects

import org.psyduck.portfolio.data.ProjectInfo

// ─── Master project list ────────────────────────────────────────────────────
// To add a new project:
//   1. Create a new file in this package (e.g. NewProject.kt)
//   2. Define a val with your ProjectInfo
//   3. Add it to this list
// The UI reads this list — that's all you need.

val allProjects: List<ProjectInfo> = listOf(
    thunderChat,
    graviton,
    eUdyaan,
    portfolio,
    floodRiskSystem,
    myPokedex
)
