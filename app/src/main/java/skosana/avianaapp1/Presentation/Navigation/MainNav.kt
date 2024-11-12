package skosana.avianaapp1.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import skosana.avianaapp1.Presentation.Home.HomePage
import skosana.avianaapp1.Presentation.Login.LoginPage
import skosana.avianaapp1.Presentation.Register.RegisterPage

@Composable
fun MainNav(
    viewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient

) {
    val navController = rememberNavController()
    val lrViewModel by lazy { LoginRegisterViewModel() }
    val context = LocalContext.current

    val registerUser =  lrViewModel.RegisterUser.collectAsState()

    NavHost(navController = navController, startDestination = "login_page") {
        composable(route = "login_page") {
            LoginPage(vIewModel = lrViewModel , NavToRegisterPage = {
                navController.navigate("register_page")

            }, LoginButtonClick = {lrViewModel.loginToFirebase(lrViewModel.loginUser)},
                NavToHomePage = {navController.navigate("home_page")}

            )
        }

        composable(route = "register_page") {
            RegisterPage(vIewModel = lrViewModel , function = {
                //navController.clearBackStack("register_page")
                navController.navigate("login_page")
                                                              } ,
                RegisterButtonClick = {
                    lrViewModel.registerToFirebase(lrViewModel.registerUser);

                }

            )
    }
        composable(route = "home_page") {
            HomePage(  context, viewModel = viewModel,birdViewModel, fusedLocationProviderClient = fusedLocationProviderClient
            )
        }

//        composable(route = "notification_settings_page") {
//            NotificationSettingPage(
//            )
//        }


    }
}