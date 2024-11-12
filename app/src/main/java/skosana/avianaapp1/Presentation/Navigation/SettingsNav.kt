package skosana.avianaapp1.Presentation.Navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import skosana.avianaapp1.Features.Settings.LanguageSettings.LanguageSettingsImpl
import skosana.avianaapp1.Features.Settings.MeasurementUnitSettings.MeasurementUnitSettingsImpl
import skosana.avianaapp1.Features.Settings.NotificationSettings.NotificationSettingImpl
import skosana.avianaapp1.Features.Settings.SpeciesRegionSettings.BirdSpeciesRegionImpl
import skosana.avianaapp1.Presentation.Settings.ComponentsNavTo.NotificationSettingPage
import skosana.avianaapp1.Presentation.Settings.ComponentsNavTo.PrivacySettingsPage
import skosana.avianaapp1.Presentation.Settings.SettingsFull
import skosana.avianaapp1.Presentation.Settings.componentsNavToBirdPref.BirdLangSettingsPage
import skosana.avianaapp1.Presentation.Settings.componentsNavToBirdPref.BirdSpeciesRegionSettingsPage
import skosana.avianaapp1.Presentation.Settings.componentsNavToBirdPref.BirdingDistanceMeasureIn

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SettingsNav(mainNavController: NavHostController,settingsViewModel: SettingsViewModel) {
    val Name = CoroutineName("SettingsNav")
    val Scope = CoroutineScope(Dispatchers.IO + Name)
    val navController = rememberNavController()
    val context = LocalContext.current
    val notificationsSettings = NotificationSettingImpl(context)
    val languageSettings = LanguageSettingsImpl(context)
    val speciesRegionSettings = BirdSpeciesRegionImpl(context)
    val measureUnitSettings = MeasurementUnitSettingsImpl(context)


    //Scope.launch {
        val settingsViewModel by lazy {SettingsViewModel(notificationsSettings,
            languageSettings ,
            speciesRegionSettings ,
            measureUnitSettings
        )
   // }

    }

    NavHost(navController = navController, startDestination = "settings_full" ) {
        //Settings Page With Navigation to other Settings
        composable(route = "settings_full") {
           // LaunchedEffect(true) {
                SettingsFull(NavToSettingsNotifications = {

                    navController.navigate("setting_notification_page")


                                                          }
                    , NavToSettingsPrivacy = {
                        navController.navigate("setting_privacy_page")

                                             } ,
                    NavToBirdLangSettings = {
                        navController.navigate("setting_BirdLang_page")

                                            },
                    NavToSpecieRegionSettings = {
                        navController.navigate("setting_SpeciesRegion_page")

                                                },
                    NavBackClick = {
                        navController.popBackStack()

                                   } ,
                    NavToMeasureInSettings = {
                        navController.navigate("setting_measureIn_page")

                    }
                )
         //   }

        }
        //Notification Settings Page
        composable(route = "setting_notification_page") {
            NotificationSettingPage(context ,settingsViewModel
            ) {
                navController.navigate("settings_full") }
        }
        composable(route = "setting_privacy_page") {
            PrivacySettingsPage(context
            ) { navController.navigate("settings_full") }
        }

        //Bird Lang Settings Language
        composable(route = "setting_BirdLang_page") {
            BirdLangSettingsPage(context = context,
                settingsViewModel = settingsViewModel ,
                NavBackClick = { navController.popBackStack() }
            )
        }

        //region Settings Page
        composable(route = "setting_SpeciesRegion_page") {
            BirdSpeciesRegionSettingsPage(context = context ,
                settingsViewModel = settingsViewModel , NavBackClick = {
                    navController.navigate("settings_full")
                }
            )
        }

        //Measure Unit Setting  in kilometers Nav
        composable(route = "setting_measureIn_page") {
            BirdingDistanceMeasureIn(context = context ,
                settingsViewModel = settingsViewModel , NavBackClick = {
                    navController.navigate("settings_full")
                    navController.popBackStack()
                    navController.navigateUp()

                }
            )
        }



    }
}