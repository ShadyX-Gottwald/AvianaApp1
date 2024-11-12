package skosana.avianaapp1.Presentation.Settings.componentsNavToBirdPref

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.Presentation.Common.ToastMsg
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel
import skosana.avianaapp1.Presentation.Settings.components.SettingsTopBar
import skosana.avianaapp1.Presentation.Settings.constants.BirdPrefSpeciesRegion

@Composable
fun BirdSpeciesRegionSettingsPage(
    context: Context,
    settingsViewModel: SettingsViewModel,
    NavBackClick: () -> Unit
) {

    //init settings obj to observe
    val settings by settingsViewModel.speciesRegion.collectAsState(initial = "Southern Africa")
    val contextLocal = LocalContext.current
    val speciesRegionPrefSwitch by settingsViewModel.speciesRegionsPrefSwitch.collectAsState(initial = true)
    var isToggled by remember { mutableStateOf(speciesRegionPrefSwitch) }
    Scaffold (
        topBar = { SettingsTopBar {
            NavBackClick()
        }
        }

    ){

        Column(
            modifier = Modifier
                .fillMaxSize(1F)
                .background(Color.White)
                .padding(vertical = 6.dp)
                .verticalScroll(rememberScrollState())

        ) {
            Spacer(modifier = Modifier.padding(it))
            Row (
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
                Text(text = "Notification Settings", color = Color.Black , fontSize = 14.sp, fontWeight = FontWeight.W800 )
                TextButton(onClick = {
                   // settingsViewModel.updateBirdPrefLang(settings)

                }) {
                    Text(text = "Save" ,color = Color.Blue)
                }
            }


            //Iterate notification Settings
            BirdPrefSpeciesRegion.Regions.forEachIndexed { index, region ->

                ListItem(headlineContent = { Text(text = region) } , leadingContent = {
                    Icon(imageVector = Icons.Default.Language, contentDescription = null)
                }, trailingContent = {
                    Checkbox(checked = if(settings == region) isToggled else false
                        , onCheckedChange = {
                            isToggled = it
                            settingsViewModel.updateSpeciesRegion(region)
                            //isToggled = it
                            ToastMsg(contextLocal ,"$region  Selected.").show()
                        })
                } )
            }

        }


    }
}