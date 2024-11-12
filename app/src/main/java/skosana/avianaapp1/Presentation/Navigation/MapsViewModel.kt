package skosana.avianaapp1.Presentation.Navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import io.ktor.utils.io.tryCopyException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import skosana.avianaapp1.Domain.Models.Hotspots.BirdHotspot
import skosana.avianaapp1.Domain.Models.NotableBirdSightings.NotableBirdSightings
import skosana.avianaapp1.Features.Core.remote.EbirdApiClient
import skosana.avianaapp1.Features.Core.remote.GeocodeApiClient
import skosana.avianaapp1.Features.Core.remote.HotspotAPIClient
import skosana.avianaapp1.Features.Core.remote.constants.GeocodeRoutes
import skosana.avianaapp1.Features.Core.remote.models.Post
import skosana.avianaapp1.Features.GoogleMap.Constants.BottomSheetOptions
import skosana.avianaapp1.Features.GoogleMap.GeoCoding.models.DTOs.GeocodeToAddressDTO
import skosana.avianaapp1.Features.GoogleMap.Utils.LocationUtils
import skosana.avianaapp1.Features.GoogleMap.models.MapLoadState
import skosana.avianaapp1.Utils.NetworkResponse
import skosana.avianaapp1.Utils.Resource


private val TAG = "MapsVm"

class MapsViewModel(
    private val fusedLocationProviderClient1: FusedLocationProviderClient
) : ViewModel() {

    var locationState by mutableStateOf<LatLng>(
        LatLng(0.0 , 0.0)
    )

    //Map Settings
    private val _mapUiSettings = MutableStateFlow(MapUiSettings(
        zoomControlsEnabled = true , mapToolbarEnabled = true))
    val mapUiSettings = _mapUiSettings.asStateFlow()

    private val _mapType = MutableStateFlow(MapType.NORMAL)
    val mapType = _mapType.asStateFlow()
    //HomePartialBottomSheet
    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet = _showBottomSheet.asStateFlow()
    //HomePartialBottomSheet Options
    private val _bottomSheetOptions = MutableStateFlow(BottomSheetOptions.HOTSPOT_INFO)
    val bottomSheetOptions = _bottomSheetOptions.asStateFlow()

    //UserLocation Variables
    private var _mapState:MutableStateFlow<LatLng?> = MutableStateFlow(null)
    var mapState = _mapState.asStateFlow()
    //Map load State
    private val _isMapLoaded = MutableStateFlow(false)
    var isMapLoaded = _isMapLoaded.asStateFlow()
    private val _mapLoadState = MutableStateFlow<MapLoadState>(MapLoadState.Loading)
    var mapLoadState = _mapLoadState.asStateFlow()


    //User Address
    private val _address: MutableStateFlow<String> = MutableStateFlow("")
    var Address = _address.asStateFlow()
    //Hotspots
    private val _hotspots:MutableStateFlow<NetworkResponse<List<BirdHotspot>>> = MutableStateFlow(NetworkResponse.Idle(emptyList<BirdHotspot>()))
    var Hotspots = _hotspots.asStateFlow()
    private val _hotspotsSimple: MutableStateFlow<List<BirdHotspot>> = MutableStateFlow(emptyList())
    var HotspotsSimple = _hotspotsSimple.asStateFlow()
    private val _selectedHotspot: MutableStateFlow<BirdHotspot?> = MutableStateFlow(null)
    var selectedHotspot = _selectedHotspot.asStateFlow()
    private val _subRegionCode: MutableStateFlow<String> = MutableStateFlow("")
    var subRegionCode = _subRegionCode.asStateFlow()
    private val _countyCode: MutableStateFlow<String> = MutableStateFlow("")
    val countyCode = _countyCode.asStateFlow()
    // Notable Sightings Selected value to view on Map
    private val _selectedNotableBirdSighting: MutableStateFlow<NotableBirdSightings?> = MutableStateFlow(null)
    var SelectedNotableBirdSighting = _selectedNotableBirdSighting.asStateFlow()

    //Bottom SHeet
    suspend fun ShowBottomSheet() {
        if(_showBottomSheet.value == false) {
            _showBottomSheet.value = true
           // _selectedHotspot.value =null
        }
        else {
            _showBottomSheet.value = false
            _selectedHotspot.value =null

        }
    }

    suspend fun SelectHotspot(hotspot: BirdHotspot) {
        _selectedHotspot.value = hotspot
    }

    //

   fun GetAddressFromApi() {

       try {
           var currentLocation = MutableStateFlow(LocationUtils.getDefaultLocation())
           var userAddress = MutableStateFlow(LocationUtils.getPosition(currentLocation.value))
           viewModelScope.launch(Dispatchers.IO) {
               val connect = GeocodeApiClient.GeocodeService
               Log.i(TAG, userAddress.value.latitude.toString())
               Log.i(TAG, userAddress.value.longitude.toString())
               Log.i(TAG, userAddress.value.toString())
               Log.i(TAG, "${userAddress.value.latitude},${userAddress.value.longitude.toString()}")

               val response = connect.GeocodeLatLongToAddress(
                   "${userAddress.value.latitude},${userAddress.value.longitude.toString()}",
                   GeocodeRoutes.API_KEY
               )

               if(response.isSuccessful) {
                   Log.i(TAG, response.toString())
                   val address = response.body()!!.GeocodeToAddressDTO()
                   Log.d(TAG, address.toString())
               }
               else {
                   throw Exception(response.message())
               }

           }


       }
       catch(e:Exception) {
           Log.i(TAG, e.message.toString())
       }


   }

    @SuppressLint("SuspiciousIndentation")
    fun NearbyHotspots() {
        val connect = HotspotAPIClient.HotspotService
        // _hotspots.value = NetworkResponse.Loading
        var currentLocation = MutableStateFlow(LocationUtils.getDefaultLocation())
        var userAddress = MutableStateFlow(LocationUtils.getPosition(currentLocation.value))

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val map: Map<String, String> = mapOf(EbirdApiClient.Key to EbirdApiClient.Value)
                val response = connect.getHotspotLocations(map, userAddress.value.latitude, userAddress.value.longitude)

                Log.d(TAG, response.code().toString())
                Log.d(TAG, response.toString())
                Log.d(TAG, response.headers().toString())

                if (response.isSuccessful) {
                    val hotspots = response.body()!!
                    // if (hotspots != null) {
                    _hotspotsSimple.emit(hotspots)
                    _subRegionCode.emit(hotspots[0].subnational1Code)
                    _countyCode.emit(hotspots[0].countryCode)
                    //    }


                } else {

                    throw Exception(response.message())

                }
            } catch (e: Exception) {
                Log.i(TAG, e.message.toString())
            }

        }
    }
}