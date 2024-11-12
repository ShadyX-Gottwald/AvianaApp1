package skosana.avianaapp1.Features.GoogleMap.componentsMaps

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import skosana.avianaapp1.Features.GoogleMap.Utils.LocationUtils
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapSimple(
    currentLocation: Location,
    mapsViewModel: MapsViewModel
) {
    val address = mapsViewModel.Address.collectAsState()
    LaunchedEffect(true){
        mapsViewModel.NearbyHotspots()
        mapsViewModel.GetAddressFromApi()

    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom( LocationUtils.getPosition(currentLocation), 10.5f)
    }

    //Map Settings
    val mapUiSettings = mapsViewModel.mapUiSettings.collectAsState()
    val mapType = mapsViewModel.mapType.collectAsState()
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = MapProperties(mapType = mapType.value,isMyLocationEnabled = true, isBuildingEnabled = true),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings.value,

        ) {
        //Text(text = address)
        Marker(
            state = MarkerState(position = LocationUtils.getPosition(currentLocation)),
            title = address.value,

            )
        ShowNearbyHotspots(mapsViewModel)

    }
}

@Composable
fun ShowNearbyHotspots(viewModel: MapsViewModel) {
    val Name = CoroutineName("HotSpot")
    val Scope = CoroutineScope(Name)
   val nearby_hotspots = viewModel.HotspotsSimple.collectAsState()
    var slectedHotspot = viewModel.selectedHotspot.collectAsState()

    nearby_hotspots.value.forEach { hotspot ->
        Marker(
            state = MarkerState(position = LatLng(hotspot.lat, hotspot.lng)),
            title = hotspot.locName,
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
            snippet = hotspot.subnational1Code,
            onInfoWindowClick = {
                Scope.launch {
                    viewModel.SelectHotspot(hotspot)
                    viewModel.ShowBottomSheet()
                }

            }
            ,onInfoWindowLongClick = {

            }



        )

    }



}