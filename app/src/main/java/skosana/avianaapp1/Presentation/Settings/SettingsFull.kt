package skosana.avianaapp1.Presentation.Settings

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.Presentation.Settings.components.SettingsAccSection
import skosana.avianaapp1.Presentation.Settings.components.SettingsBirdPrefSection

@Composable
fun SettingsFull(
    NavToSettingsNotifications: () -> Unit,
    NavToSettingsPrivacy: () -> Unit,
    NavToBirdLangSettings: () -> Unit,
    NavToSpecieRegionSettings: () -> Unit ,
    NavToMeasureInSettings: () -> Unit ,
    NavBackClick: () -> Unit
) {

    Scaffold {
        Column (
            modifier = Modifier
                .padding(
                     end = it.calculateBottomPadding(), top = it.calculateTopPadding()
                )
                .scrollable(rememberScrollState(),Orientation.Vertical)
            ,

        ){


                Text(text = "Account", color = Color.Black ,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W800  )
                SettingsAccSection( NavToNotificationSettings = NavToSettingsNotifications ,
                    NavToPrivacySettings =  NavToSettingsPrivacy )
                Text(text = "Birding Preferences", color = Color.Black , fontSize = 25.sp, fontWeight = FontWeight.W800 )
                SettingsBirdPrefSection(NavToBirdLangSettings = NavToBirdLangSettings
                    , NavToSpeciesRegion = NavToSpecieRegionSettings ,
                    NavToMeasureUnit = NavToMeasureInSettings
                )


        }


    }


}