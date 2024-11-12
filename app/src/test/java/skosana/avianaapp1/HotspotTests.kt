package skosana.avianaapp1

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import skosana.avianaapp1.Features.Core.remote.EbirdApiClient

class HotspotTests {
    private val Name = CoroutineName("Test")
    private val Scope = CoroutineScope(Name)
    @Test
    fun ConnectToApi() {
        val connect = EbirdApiClient.HotspotService

        Scope.launch(Dispatchers.IO) {
           // val response = connect.getHotspotLocations(40.714224,-73.961452)
          //  assertEquals(response.isSuccessful , true)
        }
       // val response = connect.getHotspotLocations(40.714224,-73.961452)
    }

    @Test
    fun GetHotspotsFromApi() {
        val connect = EbirdApiClient.HotspotService

        Scope.launch(Dispatchers.IO) {
//            val response = connect.getHotspotLocations(40.714224,-73.961452)
//
//            if (response.isSuccessful) {
//                val hotspots = response.body()
//                assertEquals(hotspots?.size!! > 0, true)
//            }
           // assertEquals(response.isSuccessful , true)
        }
    }
}