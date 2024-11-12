package skosana.avianaapp1.Presentation.Home.TabComponents

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import skosana.avianaapp1.Presentation.Home.constants.HomeTabbedConstants
import skosana.avianaapp1.Presentation.Navigation.BirdViewModel
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.Peach

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun BirdsTab(
    mapsViewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    context: Context,
    settingsViewModel: SettingsViewModel
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = HomeTabbedConstants.TabbedNavItems

    Column(modifier = Modifier.fillMaxWidth() ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly


    ) {

        ScrollableTabRow(selectedTabIndex = tabIndex ,

             modifier = Modifier.fillMaxWidth()
                 .background(DarkPurpleBackGround),
            containerColor = DarkPurpleBackGround,

            indicator = {
                    tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = Peach)
            }

            ) {

            tabs.forEachIndexed { index, tab ->


                Tab(text = {
                    Text(tab.label , color = Peach)
                           },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        Icon(imageVector = tab.icon, contentDescription = "Tab" , tint = Peach )
                    }

                )

            }

        }
        when (tabIndex) {
            0 -> DiscoverTabComponent(mapsViewModel, birdViewModel,context,settingsViewModel,)
            1 -> BirdsTabComponent(mapsViewModel, birdViewModel,settingsViewModel)
            2 -> CreateNewTabComponent()

        }

    }
}