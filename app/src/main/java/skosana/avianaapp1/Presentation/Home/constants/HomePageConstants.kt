package skosana.avianaapp1.Presentation.Home.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SpatialAudio
import androidx.compose.material.icons.rounded.Settings
import skosana.avianaapp1.Presentation.Home.Models.BottomNavItem

private val EBIRD_API = "rhj2pqdjsgpu"

object HomePageConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home_component"
        ),
        BottomNavItem(
            label = "Birds",
            icon = Icons.AutoMirrored.Default.StarHalf,
            route = "birds_component"
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = "profile_component"
        )  ,
        BottomNavItem(
            label = "Settings",
            icon = Icons.Rounded.Settings,
            route = "settings_component"
        )


    )
}