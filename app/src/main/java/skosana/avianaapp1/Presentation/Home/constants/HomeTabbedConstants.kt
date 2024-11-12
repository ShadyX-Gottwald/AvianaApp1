package skosana.avianaapp1.Presentation.Home.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlusOne
import skosana.avianaapp1.Presentation.Home.Models.BottomNavItem

object HomeTabbedConstants {
    val TabbedNavItems = listOf(
        BottomNavItem(
            label = "Discover",
            icon = Icons.Filled.Home,
            route = "home_component"
        ),
        BottomNavItem(
            label = "Birds",
            icon = Icons.AutoMirrored.Default.StarHalf,
            route = "birds_component"
        ),
        BottomNavItem(
            label = "Create",
            icon = Icons.Filled.PlusOne,
            route = "birds_component"
        ),

    )
}