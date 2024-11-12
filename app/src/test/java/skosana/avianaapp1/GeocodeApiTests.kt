package skosana.avianaapp1

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import skosana.avianaapp1.Features.Core.remote.EbirdApiClient
import skosana.avianaapp1.Features.Core.remote.GeocodeApiClient
import skosana.avianaapp1.Features.Core.remote.constants.GeocodeRoutes
import skosana.avianaapp1.Features.GoogleMap.GeoCoding.models.DTOs.GeocodeToAddressDTO

class GeocodeApiTests {
    private val Name = CoroutineName("Test")
    private val Scope = CoroutineScope(Name)
    private val address = "277 Bedford Ave, Brooklyn, NY 11211, USA"
    @Test
    fun connectToApi() {

        Scope.launch(Dispatchers.IO) {
            val connect = GeocodeApiClient.GeocodeService
            val coordinates = "40.714224,-73.961452"
            val response = connect.GeocodeLatLongToAddress(coordinates, GeocodeRoutes.API_KEY)

            //val response = connect.GeocodeLatLongToAddress(40.714224,-73.961452,GeocodeRoutes.API_KEY)
           assertEquals(response.isSuccessful , true)
        }

    }

    @Test
    fun `277BedfordAveNYUSAddressIsCorrect`() {
       // val IGeocodeApi = EbirdApiClient.PostService

        Scope.launch(Dispatchers.IO) {
            val connect = GeocodeApiClient.GeocodeService
          //  val response = connect.GeocodeLatLongToAddress(40.714224,-73.961452,GeocodeRoutes.API_KEY)
        //    val address = response.body()?.GeocodeToAddressDTO()
            assertEquals(address , "277 Bedford Ave, Brooklyn, NY 11211, USA")

            //assertEquals(response.isSuccessful , true)
        }

    }
}