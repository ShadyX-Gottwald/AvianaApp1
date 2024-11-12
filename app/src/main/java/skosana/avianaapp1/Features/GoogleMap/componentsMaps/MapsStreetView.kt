package skosana.avianaapp1.Features.GoogleMap.componentsMaps

import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.streetview.StreetView
import com.google.maps.android.ktx.MapsExperimentalFeature
import skosana.avianaapp1.Features.GoogleMap.Utils.LocationUtils
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel

@OptIn(MapsExperimentalFeature::class)
@Composable
fun MapStreetView(
    currentLocation: Location,
    mapsViewModel: MapsViewModel
) {
    val location = remember {
        LocationUtils.getPosition(currentLocation)
    }
    //val edmontonCanadaPlace = LatLng(53.543140171924016, -113.48595856458006)
    StreetView(
        streetViewPanoramaOptionsFactory = {
            StreetViewPanoramaOptions().position(location)
        }
    )
}