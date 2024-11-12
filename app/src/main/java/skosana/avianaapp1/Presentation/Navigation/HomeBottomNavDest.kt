package skosana.avianaapp1.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.location.FusedLocationProviderClient
import skosana.avianaapp1.Features.Settings.LanguageSettings.LanguageSettingsImpl
import skosana.avianaapp1.Features.Settings.MeasurementUnitSettings.MeasurementUnitSettingsImpl
import skosana.avianaapp1.Features.Settings.NotificationSettings.NotificationSettingImpl
import skosana.avianaapp1.Features.Settings.SpeciesRegionSettings.BirdSpeciesRegionImpl
import skosana.avianaapp1.Presentation.Home.components.BirdComponentDest
import skosana.avianaapp1.Presentation.Home.components.HomeComponentDest
import skosana.avianaapp1.Presentation.Profile.ProfileComponentDest
import skosana.avianaapp1.Presentation.Settings.SettingsComponentDest

@Composable
fun HomeBottomNavDest(
    navController: NavHostController,
    viewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient
) {

    //Set up Controller , ViewModel and Context
   // val navController = rememberNavController()
    val homeViewModel by lazy {HomeViewModel() }
    //val mapsViewModel by lazy {MapsViewModel(fusedLocationProviderClient)}
    val context = LocalContext.current
    val notifications: NotificationSettingImpl = NotificationSettingImpl(context)
    val language: LanguageSettingsImpl = LanguageSettingsImpl(context)
    val speciesRegionSettings = BirdSpeciesRegionImpl(context)
    val measureUnitSettings = MeasurementUnitSettingsImpl(context)
    val settingsViewModel by lazy {SettingsViewModel(notifications,language ,
        speciesRegionSettings , measureUnitSettings )
    }


    NavHost(navController = navController, startDestination = "home_component") {

        composable(route = "home_component") {
                HomeComponentDest(NavToBirdComponent
                = { navController.navigate("birds_component") },
                    mainNavController = navController,
                    settingsViewModel,
                    viewModel,
                    fusedLocationProviderClient,
                    context

                )
        }

        composable(route = "birds_component") {
            BirdComponentDest(mapsViewModel = viewModel, birdViewModel,context ,settingsViewModel)
        }

        composable(route = "profile_component") {
            ProfileComponentDest()
        }

        composable(route = "settings_component") {
            SettingsComponentDest(mainNavController = navController ,
                NavToNotificationSettings = {navController.navigate("")} , NavBackToClick = {
                    navController.popBackStack()
                }, settingsViewModel)
        }
    }
}