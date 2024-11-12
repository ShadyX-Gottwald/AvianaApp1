package skosana.avianaapp1.Presentation.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skosana.avianaapp1.Presentation.Navigation.SettingsNav
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel

import skosana.avianaapp1.Presentation.Settings.components.SettingsTopBar
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.Peach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsComponentDest(mainNavController: NavHostController ,
                          NavToNotificationSettings: () -> Unit ,
                          NavBackToClick: () -> Unit,
                          settingsViewModel: SettingsViewModel

                          ) {

    Scaffold(
        modifier = Modifier.fillMaxSize(1F),

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding()
                .fillMaxSize()
                //.verticalScroll(rememberScrollState(0))
                .background(Color.White),

        ) {


            SettingsNav(mainNavController = mainNavController, settingsViewModel = settingsViewModel)
           // SettingsFull (NavToNotificationSettings)

        }

    }

}

