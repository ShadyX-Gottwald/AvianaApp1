package skosana.avianaapp1.Features.GoogleMap.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.flow.MutableStateFlow
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel


@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Composable
fun  MapViewComponent(
    mapsViewModel: MapsViewModel,
    context: Context,
  //  locationProviderClient: FusedLocationProviderClient
) {

    LaunchedEffect(true) {
       // mapsViewModel.getCurrentLocation()
    }
    var isMapLoaded by remember { mutableStateOf(false) }
    var hasLocationPermission by remember { mutableStateOf(false) }

    var viewlocation = mapsViewModel.mapState.collectAsState()


    var location  = remember {
        MutableStateFlow(LatLng(0.1 ,0.1))
    }

    var mapProperties = remember {
        MutableStateFlow(MapProperties(

           mapType =  MapType.NORMAL
        ))
    }



    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth(1F)
                .fillMaxHeight(0.93F)
            ,
            properties = mapProperties.value //viewModel.state.properties,
            ,uiSettings = MapUiSettings(zoomControlsEnabled = false),
            onMapLoaded = { isMapLoaded = true },
             //cameraPositionState = cameraPositionState,

            onMapLongClick = { click ->
                //viewModel.onEvent(MapEvent.OnMapLongClick(click))

                Toast.makeText(
                    context, "${click.latitude} , ${click.longitude}", Toast.LENGTH_SHORT
                ).show()
               // viewModel.onEvent(MapEvent.OnMapLongClick(click))

            },

            onMapClick = {

            }
        ) {




            viewlocation.value?.let { MarkerState(it) }?.let {
                Marker(
                    state = it,
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_RED
                    ) ,
                    title = "Location ${mapsViewModel.locationState.latitude} , ${mapsViewModel.locationState.longitude}",
                ){
                    it.showInfoWindow()
                }
            }

            //viewModel.spots.add(ParkingSpot(res.longitude , res.latitude ,2))
//            viewModel.
//            spots.forEach { spot ->
//                Marker(
//                    state = MarkerState(LatLng(spot.lat, spot.lng)),
//                    title = "Parking Spot ${spot.lat} , ${spot.lng}",
//                    snippet = "LongClick TO Delete",
//                    onInfoWindowClick = {
//                        viewModel.onEvent(MapEvent.OnInfoWindowLongClick(spot))
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


    }
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {

        ExtendedFloatingActionButton(
            text = { Text(text = "MyLocation") },
            icon = { Icon(imageVector = Icons.Filled.Place, contentDescription = "") },
            onClick = {



            })

    }
    if (!isMapLoaded) {
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize(),
            visible = !isMapLoaded,
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

    if (hasLocationPermission) {
        Text("Location permission granted")
    } else {
        Text("Requesting location permission...")
        Button(onClick = {

        }) {
            Text("Request permission?...")

        }
    }
}



