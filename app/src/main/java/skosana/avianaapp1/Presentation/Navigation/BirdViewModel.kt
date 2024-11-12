package skosana.avianaapp1.Presentation.Navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import skosana.avianaapp1.Domain.Models.BirdWithTaxonomy.TaxonomicBird
import skosana.avianaapp1.Domain.Models.NotableBirdSightings.NotableBirdSightings
import skosana.avianaapp1.Features.Core.remote.EbirdApiClient
import skosana.avianaapp1.Features.Core.remote.HotspotAPIClient
import skosana.avianaapp1.Features.GoogleMap.Utils.LocationUtils

private val TAG = "BirdsVM"
class BirdViewModel: ViewModel() {

    //Birds Default Search Results
    private val _birdsDefault: MutableStateFlow<List<TaxonomicBird>> = MutableStateFlow(emptyList())
    val BirdsDefault = _birdsDefault.asStateFlow()
    //Notable Bird Sightings
    private val _notableBirdSightings: MutableStateFlow<List<NotableBirdSightings>> = MutableStateFlow(emptyList())
    val NotableBirdSightings = _notableBirdSightings.asStateFlow()
    //Bird List map of SciName to Photo Url
    private val _birdPhotoUrls: MutableStateFlow<Map<String, String>> = MutableStateFlow(emptyMap())
    val BirdPhotoUrls = _birdPhotoUrls.asStateFlow()

    //Get Language Settings

    private val _languageSettings: MutableStateFlow<String> = MutableStateFlow("")
    val LanguageSettings = _languageSettings.asStateFlow()


    //Get Bird Taxonomic Data With Birds

    @SuppressLint("SuspiciousIndentation")
    fun getTaxonomicBirds(locale:String) {
        val connect = HotspotAPIClient.HotspotService

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val map: Map<String, String> = mapOf(EbirdApiClient.Key to EbirdApiClient.Value)
                val response = connect.getDefaultTaxonomicBirds(map , locale)

                Log.d(TAG, response.code().toString())
                Log.d(TAG, response.toString())
                Log.d(TAG, response.headers().toString())

                if (response.isSuccessful) {
                    val birds = response.body()!!

                    _birdsDefault.value = birds
                    Log.d(TAG, birds.toString())

                } else {

                    throw Exception(response.message())

                }
            } catch (e: Exception) {
                Log.i(TAG, e.message.toString())
            }

        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun NotableBirdSightingsByRegion(subNationalCode: String, countyCode: String,appLocale: String) {
        val connect = HotspotAPIClient.HotspotService
        // _hotspots.value = NetworkResponse.Loading
        var currentLocation = MutableStateFlow(LocationUtils.getDefaultLocation())
        var userAddress = MutableStateFlow(LocationUtils.getPosition(currentLocation.value))

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val map: Map<String, String> = mapOf(EbirdApiClient.Key to EbirdApiClient.Value)
                val response = connect.getNotableBirdSightings(
                    map,userAddress.value.latitude,userAddress.value.longitude,6 ,
                    50,appLocale)

                Log.d(TAG, response.code().toString())
                Log.d(TAG, response.toString())
                Log.d(TAG, response.headers().toString())

                if (response.isSuccessful) {
                    val sightings = response.body()!!
                    _notableBirdSightings.value = sightings
                    Log.d(TAG, sightings.toString())


                } else {

                    throw Exception(response.message())

                }
            } catch (e: Exception) {
                Log.i(TAG, e.message.toString())
            }

        }
    }

}