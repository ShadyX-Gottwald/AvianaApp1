package skosana.avianaapp1.Presentation.Home.components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import skosana.avianaapp1.Features.GoogleMap.common.MapActionMenu
import skosana.avianaapp1.Features.GoogleMap.components.MapScreen2
import skosana.avianaapp1.Features.GoogleMap.models.MapLoadState


import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround


@Composable
fun  HomeComponentDest(
    NavToBirdComponent: () -> Unit,
    mainNavController: NavHostController,
    settingsViewModel: SettingsViewModel,
    mapsViewModel: MapsViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient,
    context: Context,

    ) {

    val context = LocalContext.current
    val settings = settingsViewModel.settings.collectAsState(initial = false)
    val settingsLang = settingsViewModel.birdingLanguage.collectAsState(initial = "")
    val settingsRegion = settingsViewModel.speciesRegion.collectAsState(initial = "")


    Column(
        modifier = Modifier
            .fillMaxWidth(1F)
            .fillMaxHeight(0.86F)
            .background(DarkPurpleBackGround),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LocationScreen(viewModel = mapsViewModel,fusedLocationProviderClient ,settingsViewModel)
        //Text(text = getUserLocation(context).toString() ,color = Color.Black)


//        // Text to Display the current Screen
//        Text(text = settings.value.toString(), color = Color.Black)
//        Text(text = settingsLang.value.toString() ,color = Color.Black)
//        Text(text = settingsRegion.value.toString() ,color = Color.Black)
//


    }
}

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition",
    "CoroutineCreationDuringComposition"
)
@Composable
fun LocationScreen(
    viewModel: MapsViewModel,
    fusedLocationProviderClient: FusedLocationProviderClient,
    settingsViewModel: SettingsViewModel) {

    val _makerList: MutableList<LatLng> =   mutableListOf<LatLng>(
        LatLng(32.33 , -102.877),
        LatLng(32.00 , -102.66)
    )

    //ShowLoadedMap(viewModel , CameraPositionState , context)

    MapScreen2(fusedLocationProviderClient = fusedLocationProviderClient , mapsViewModel = viewModel,)

}

@Composable
private fun LoadMap(viewModel: MapsViewModel) {
    val loadingState = viewModel.isMapLoaded.collectAsState()
    AnimatedVisibility(
        modifier = Modifier
            .fillMaxSize(),
        visible = !loadingState.value,
        enter = EnterTransition.None,
        exit = fadeOut()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .wrapContentSize()
        )
    }
}

@Composable
private  fun ShowLoadedMap(
    viewModel: MapsViewModel,
    CameraPositionState: CameraPositionState,
    context: Context
) {
     var isMapLoaded by remember { mutableStateOf(false) }
    AnimatedContent(
        viewModel.mapState, label = "Get User Location"

    ) { state ->


        //Map Settings
        val initialZoom = 6f
        val finalZoom = 15f
        val l =  viewModel.mapState.collectAsState()
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(l.value!!, 5f)
        }
        val mapUiSettings by remember { mutableStateOf(MapUiSettings()) }
        val mapProperties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }
        val mapState by remember { mutableStateOf(viewModel.mapLoadState) }
        val MapState = viewModel.mapLoadState.collectAsState()

        //User Location
       // val address  = viewModel.Address.collectAsState()

        when(MapState.value){

            is MapLoadState.Loading -> {
                LoadMap(viewModel)
                viewModel.mapLoadState.map {
                    MutableStateFlow<MapLoadState>(MapLoadState.Loading)
                }
            }
            is MapLoadState.Loaded -> {


            }
            else -> Unit
        }



        Box {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = CameraPositionState,
                uiSettings = mapUiSettings,
                properties = mapProperties ,
                onMapLoaded = {


                    CoroutineScope(Dispatchers.Default).launch {
                        //viewModel.GetAddressFromApi(viewModel.mapState.value)
                        if(viewModel.isMapLoaded.value){
                            CameraPositionState.animate(
                                update = CameraUpdateFactory.newCameraPosition(
                                    CameraPosition(l.value!!, finalZoom, 0f, 0f)
                                ),
                                durationMs = 1000
                            )

                        }
                    }
                },

                onMapClick = {
                    //Log.d(TAG, "Coordinate clicked: $it")
                    // pos2.add(it)
                    // pos = it
                },
            ){
                val markerState by remember{
                    mutableStateOf(
                        viewModel.mapState!!.value?.let { MarkerState(position = it) }
                    )

                }
                markerState?.let {
                    Marker(
                        state = it,
                        icon = BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_RED
                        ) ,
                        // title = "Location: ${address.value}",

                    ){
                        // viewModel.GetAddressFromApi(it.position)
                        it.showInfoWindow()
                    }
                }

                //Show Other Location Sites
//            pos2.forEach {
//                Marker(
//                    state = MarkerState(it),
//                    title = "Parking Spot ${it.latitude} , ${it.longitude}",
//                    snippet = "LongClick TO Delete",
//                    onInfoWindowClick = {
//                        //viewModel.onEvent(MapEvent.OnInfoWindowLongClick(spot))
//                    },
//
//                    icon = BitmapDescriptorFactory.defaultMarker(
//                        BitmapDescriptorFactory.HUE_RED
//                    )
//
//                ) {
//                    it.showInfoWindow()
//
//                }
//
//            }

            }
            MapActionMenu(viewModel)
        }

    }

}
