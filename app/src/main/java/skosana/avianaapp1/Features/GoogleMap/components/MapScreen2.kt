package skosana.avianaapp1.Features.GoogleMap.components

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import skosana.avianaapp1.Features.Core.remote.EbirdApiClient
import skosana.avianaapp1.Features.Core.remote.GeocodeApiClient
import skosana.avianaapp1.Features.GoogleMap.Constants.MapOptions
import skosana.avianaapp1.Features.GoogleMap.Utils.LocationUtils
import skosana.avianaapp1.Features.GoogleMap.componentsMaps.MapSimple
import skosana.avianaapp1.Features.GoogleMap.componentsMaps.MapStreetView
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel
import skosana.avianaapp1.R


@Composable
fun MapScreen2(
    fusedLocationProviderClient: FusedLocationProviderClient,
    mapsViewModel: MapsViewModel,

) {

    var currentLocation by remember { mutableStateOf(LocationUtils.getDefaultLocation()) }

    val cameraPositionState = rememberCameraPositionState()
    cameraPositionState.position = CameraPosition.fromLatLngZoom(
        LocationUtils.getPosition(currentLocation), 12f)

    var result by remember { mutableStateOf("Empty")}

    val geoLocation = GeocodeApiClient.GeocodeService
    val Hotspots = EbirdApiClient.HotspotService

    LaunchedEffect(true) {
        //mapsViewModel.NearbyHotspots()
       // mapsViewModel.GetPostFromApi()
       // mapsViewModel.GetAddressFromApi()
    }


    var requestLocationUpdate by remember { mutableStateOf(true)}

    MyGoogleMap(
        currentLocation,
        cameraPositionState,
        onGpsIconClick = {
            requestLocationUpdate = true
        },mapsViewModel,
        result
    )

    if(requestLocationUpdate) {
        LocationPermissionAndSettingDialog (
            updateCurrentLocation = {
                requestLocationUpdate = false
                LocationUtils.requestLocationResultCallback(fusedLocationProviderClient) { locationResult ->

                    locationResult.lastLocation?.let { location ->
                        currentLocation = location
                    }

                }
            }
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun MyGoogleMap(
    currentLocation: Location,
    cameraPositionState: CameraPositionState,
    onGpsIconClick: () -> Unit,
    mapsViewModel: MapsViewModel,
    result: String
) {
    //State To choose Map Type
    var selectedMapOption by rememberSaveable { mutableStateOf(MapOptions.MAP_SIMPLE) }
    val showBottomSheet  =  mapsViewModel.showBottomSheet.collectAsState()

     val Name = CoroutineName("Test")
     val Scope = CoroutineScope(Name)


    var address by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                items = listOf(
                    FabButtonItem(
                        iconRes = Icons.Filled.Map,
                        label = "Simple Map"
                    ) {
                        selectedMapOption = MapOptions.MAP_SIMPLE
                    },


                    FabButtonItem(
                        iconRes = Icons.Filled.Streetview,
                        label = "Street View"
                    ) {
                        selectedMapOption = MapOptions.MAP_STREET_VIEW
                    }
                    ,
                    FabButtonItem(iconRes = Icons.Filled.RemoveRedEye,label = "Bottom Sheet"){
                        selectedMapOption = MapOptions.MAP_BOTTOM_SHEET
                        Scope.launch {
                            if(showBottomSheet.value == false){
                                mapsViewModel.ShowBottomSheet()
                            }
                            else {
                                mapsViewModel.ShowBottomSheet()
                            }
                            ///mapsViewModel.showBottomSheet.value = true
                        }

                    }
                ),
                fabIcon = FabButtonMain(),
                fabOption = FabButtonSub()
            )
        },



    ) { contentPadding ->

        Box(
            modifier = Modifier.padding(contentPadding)
        ) {


            //Fab Buttons
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (selectedMapOption) {
                    MapOptions.MAP_SIMPLE -> {
                        MapSimple(currentLocation,mapsViewModel)
                        HomePartialBottomSheet(viewModel = mapsViewModel)
                    }
                    MapOptions.MAP_STREET_VIEW -> MapStreetView(currentLocation,mapsViewModel)
                    MapOptions.MAP_BOTTOM_SHEET -> {
                            MapSimple(currentLocation,mapsViewModel)
                            HomePartialBottomSheet(viewModel = mapsViewModel)
                    }


                    else -> {}
                }
            }

    }


       // GpsIconButton(onIconClick = onGpsIconClick)

       // DebugOverlay(cameraPositionState)
    }

}

@Composable
private fun GpsIconButton(onIconClick: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(onClick = onIconClick) {
                Icon(
                    modifier = Modifier.padding(bottom = 100.dp, end = 20.dp),
                    painter = painterResource(id = R.drawable.baseline_gps_fixed_24),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun DebugOverlay(
    cameraPositionState: CameraPositionState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        val moving =
            if (cameraPositionState.isMoving) "moving" else "not moving"
        Text(
            text = "Camera is $moving",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray)
        Text(
            text = "Camera position is ${cameraPositionState.position}",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray)
    }
}