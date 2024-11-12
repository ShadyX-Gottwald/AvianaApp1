package skosana.avianaapp1.Presentation.Home

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import skosana.avianaapp1.Features.Notifications.NotificationService
import skosana.avianaapp1.Presentation.Home.components.HomeBottomNav
import skosana.avianaapp1.Presentation.Navigation.BirdViewModel
import skosana.avianaapp1.Presentation.Navigation.HomeBottomNavDest
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel

var TAG = "MainActivity"
const val REQUEST_CODE_NOTIFICATION_PERMISSIONS = 11
@Composable
fun HomePage(
   // navController: NavController ,
    context: Context,
    viewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient

) {


    val notificationService = NotificationService(LocalContext.current.applicationContext)


    val nav = rememberNavController()
    val context = LocalContext.current
    val navBackStackEntry by nav.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold (
        bottomBar = {
            if(currentRoute != "settings_page") {
                HomeBottomNav(nav,
                    NavToHome =
                    { nav.navigate("home_component") } ,
                    NavToBirds = {nav.navigate("birds_component")}
                )

            }

        },
        topBar = {
           // HomeTopAppBar(navController = nav)
        }
    ){

        Spacer(modifier = Modifier.padding(it))
        HomeBottomNavDest(navController = nav,viewModel ,birdViewModel ,fusedLocationProviderClient)
    }
}
