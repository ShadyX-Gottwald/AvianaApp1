package skosana.avianaapp1.Presentation.Settings.ComponentsNavTo

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel
import skosana.avianaapp1.Presentation.Settings.components.SettingsTopBar
import skosana.avianaapp1.Presentation.Settings.models.SettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  NotificationSettingPage(
    context: Context,
    settingsViewModel: SettingsViewModel,
    NavBackClick: () -> Unit
) {
    // Settings Items
    val settingOption = listOf<SettingsItem>(
        SettingsItem(
            "Notifications" ,
            Icons.Default.Notifications ,
            ""
        )
    )

    //init settings obj to observe
    val settings by settingsViewModel.settings.collectAsState(initial = false)

    Scaffold (

        topBar = { SettingsTopBar {
            NavBackClick()
        }}
    ){

        Column(
            modifier = Modifier
                .fillMaxSize(1F)
                .background(Color.White)
                .padding(vertical = 6.dp)

        ) {
            Spacer(modifier = Modifier.padding(it))
            Row (
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
                Text(text = "Notification Settings", color = Color.Black , fontSize = 14.sp, fontWeight = FontWeight.W800 )
                TextButton(onClick = {
                   settingsViewModel.updateCanNotify(settings)
                }) {
                    Text(text = "Save" ,color = Color.Blue)
                }
            }


            //Iterate notification Settings
            settingOption.forEachIndexed { index, s ->
                ListItem(headlineContent = { Text(text = s.label) } , leadingContent = {
                    Icon(imageVector = s.icon, contentDescription = null)
                }, trailingContent = {
                    Switch(checked = settings, onCheckedChange = {
                        settingsViewModel.updateCanNotify(it)
                    })
                } )
            }

            Text(text = settings.toString() ,color = Color.Blue)

        }


    }

}