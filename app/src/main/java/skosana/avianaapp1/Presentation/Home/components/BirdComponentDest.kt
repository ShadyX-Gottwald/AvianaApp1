package skosana.avianaapp1.Presentation.Home.components


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import skosana.avianaapp1.Presentation.Home.TabComponents.BirdsTab
import skosana.avianaapp1.Presentation.Navigation.BirdViewModel
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel


@Composable
fun BirdComponentDest(
    mapsViewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    context: Context,
    settingsViewModel: SettingsViewModel
) {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
      //  horizontalAlignment = Alignment.CenterHorizontally,
      //  verticalArrangement = Arrangement.Center
    ) {

        BirdsTab(mapsViewModel,birdViewModel,context,settingsViewModel)
    }
}