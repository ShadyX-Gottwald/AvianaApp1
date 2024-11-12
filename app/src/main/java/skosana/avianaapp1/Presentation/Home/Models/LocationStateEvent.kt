package skosana.avianaapp1.Presentation.Home.Models

import com.google.android.gms.maps.model.LatLng


sealed class LocationStateEvent {

    data object NoPermission: LocationStateEvent()
    data object LocationDisabled: LocationStateEvent()
    data object LocationLoading: LocationStateEvent()
    data class LocationAvailable(val location: LatLng): LocationStateEvent()
    data object Error: LocationStateEvent()
}