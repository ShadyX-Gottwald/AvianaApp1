package skosana.avianaapp1.Presentation.Home.TabComponents

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import skosana.avianaapp1.Presentation.Common.BirdCardComponent
import skosana.avianaapp1.Presentation.Common.BirdProfileCard
import skosana.avianaapp1.Presentation.Navigation.BirdViewModel
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel
import skosana.avianaapp1.Presentation.Settings.Utils.LanguageSettingsUtils


@Composable
fun BirdsTabComponent(
    mapsViewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    settingsViewModel: SettingsViewModel
) {
    val Name = CoroutineName("DiscoverTabComponent")
    val Scope = CoroutineScope(Name)
    var languageSettings by remember { mutableStateOf("English") }
    LaunchedEffect(true) {
       // birdViewModel.getTaxonomicBirds()
        Scope.launch(Dispatchers.IO) {
            settingsViewModel.birdingLanguage.collect {
                    prefsLang -> Log.d("DiscoverTabComponent", "Prefs Language: $prefsLang")
                languageSettings = prefsLang

                Log.i("DiscoverTabComponent", "Language: ${languageSettings}")
                val queryLang = LanguageSettingsUtils.Languagess[languageSettings]
                Log.i("DiscoverTabComponent", "Query Language: $queryLang")
                if (queryLang != null) {
                    birdViewModel.NotableBirdSightingsByRegion(
                        mapsViewModel.subRegionCode.value,mapsViewModel.countyCode.value , queryLang
                    )
                }

            }

        }

    }
    val birds = birdViewModel.BirdsDefault.collectAsState()
    val sightings = birdViewModel.NotableBirdSightings.collectAsState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {

        sightings.value.forEachIndexed { index, notableBirdSightings ->
            item {
                BirdCardComponent(titleText = notableBirdSightings.sciName ,
                    subtitleText = notableBirdSightings.locName,
                    otherName = notableBirdSightings.comName,
                    dateText = notableBirdSightings.obsDt,
                    onViewButtonClick = {


                    }

                )
            }
        }
    }

}

@Composable
fun ShowDefaultBirds(viewModel: MapsViewModel, birdViewModel: BirdViewModel) {
    val birds = birdViewModel.BirdsDefault.collectAsState()

    birds.value.forEach {
        BirdProfileCard(

            titleText = it.comName,
            subtitleText = it.sciName,


        )
    }
}