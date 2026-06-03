package org.psyduck.portfolio.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.psyduck.portfolio.theme.PortfolioColors.Border
import org.psyduck.portfolio.theme.PortfolioColors.Blue400
import org.psyduck.portfolio.theme.PortfolioColors.Pink500
import org.psyduck.portfolio.theme.PortfolioColors.Purple500
import org.psyduck.portfolio.theme.PortfolioColors.TextSecondary
import org.psyduck.portfolio.theme.rememberJetBrainsMono

// ─── Nav items ───────────────────────────────────────────────────────────────

private data class NavItem(val label: String, val route: String)

private val navItems = listOf(
    NavItem("Home",       "/"),
    NavItem("Projects",   "/projects"),
    NavItem("Contact",    "/contact")
)

// ─── NavBar ───────────────────────────────────────────────────────────────────

@Composable
fun NavBar(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf("/") }

    val navShape = RoundedCornerShape(14.dp)

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(500)) + slideInVertically(
            animationSpec = tween(500),
            initialOffsetY = { -it }   // slides down from above
        ),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clip(navShape)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF11131A).copy(alpha = 0.92f),
                            Color(0xFF090B11).copy(alpha = 0.88f)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.18f),
                    shape = navShape
                )
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                val isSelected = selected == item.route

                // Gradient accent brush for the active pill
                val activeBrush = Brush.linearGradient(
                    listOf(Purple500, Blue400, Pink500)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .then(
                            if (isSelected)
                                Modifier.background(
                                    Brush.linearGradient(
                                        listOf(
                                            Purple500.copy(alpha = 0.25f),
                                            Blue400.copy(alpha = 0.20f)
                                        )
                                    )
                                )
                            else Modifier
                        )
                        .clickable { selected = item.route }
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.label,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.W700 else FontWeight.W400,
                        color = if (isSelected) Color.White else TextSecondary,
                        letterSpacing = 0.3.sp
                    )
                }
            }
        }
    }
}