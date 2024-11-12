package skosana.avianaapp1.Presentation.Settings.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import skosana.avianaapp1.Presentation.Settings.models.SettingsItem

object SettingsPageConstants {
    val SettingAccItems : List<SettingsItem> = mutableListOf(
        SettingsItem(
            label ="Notification" ,
            icon = Icons.Outlined.Notifications,
            route = "settings_notifications_component"
        ),
        SettingsItem(
            "Privacy" ,
            icon = Icons.Outlined.Lock ,
            route = "settings_privacy_component"
        )
    )
}

object SettingsBirdPrefConstants {
    val SettingPrefItems : List<SettingsItem> = mutableListOf(
        SettingsItem(
            "Species Language" ,
            icon = Icons.Outlined.Language,
            route = "settings_species_lang_component"
        ),
        SettingsItem(
            "Species Region" ,
            icon = Icons.Outlined.Map ,
            route = "settings_species_region_component"
        ),
        SettingsItem(
            "Kilometer Unit" ,
            icon = Icons.AutoMirrored.Filled.DirectionsWalk,
            route = "settings_species_region_component"
        )
    )
}