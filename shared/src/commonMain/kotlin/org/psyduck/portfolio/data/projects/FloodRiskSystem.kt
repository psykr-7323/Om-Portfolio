package org.psyduck.portfolio.data.projects

import androidx.compose.ui.graphics.Color
import org.psyduck.portfolio.data.ProjectInfo
import org.psyduck.portfolio.data.ProjectStatus
import org.psyduck.portfolio.data.ProjectTimelineStep

val floodRiskSystem = ProjectInfo(
    slug = "flood-risk-system",
    name = "Flood Risk System",
    description = "ML-powered flood risk dashboard processing 1.1M+ records with MLP Neural Network classification, real-time Streamlit simulation, and automated alert system.",
    status = ProjectStatus.ACTIVE,
    githubUrl = "https://github.com/psykr-7323/FloodRisk",
    stack = listOf("Python", "ML", "Streamlit", "Data Viz"),
    thumbnailColors = listOf(
        Color(0xFF0F766E),
        Color(0xFF1D4ED8),
        Color(0xFFF59E0B)
    ),
    highlight = "ML dashboard at scale",
    timeline = listOf(
        ProjectTimelineStep(
            title = "Ingest",
            subtitle = "Large dataset",
            detail = "Organized the flood records and prepared them for model training."
        ),
        ProjectTimelineStep(
            title = "Train",
            subtitle = "Classification",
            detail = "Built the neural network flow for risk prediction across large input sets."
        ),
        ProjectTimelineStep(
            title = "Simulate",
            subtitle = "Dashboard flow",
            detail = "Created a real-time Streamlit view for interactive risk inspection."
        ),
        ProjectTimelineStep(
            title = "Alert",
            subtitle = "Operational output",
            detail = "Automated alerting so the model could act as a practical decision tool."
        )
    )
)
