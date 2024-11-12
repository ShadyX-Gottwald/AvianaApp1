package skosana.avianaapp1.Features.Core.remote

import com.google.gson.Gson
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skosana.avianaapp1.Features.Core.remote.constants.EbirdRoutes
import skosana.avianaapp1.Features.Core.remote.services.IBirdHotspot

object HotspotAPIClient {

    val networkJson = Json { ignoreUnknownKeys = true }
    private fun getHotspotInstance(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(EbirdRoutes.EBIRD_HOTSPOTS_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val HotspotService : IBirdHotspot = getHotspotInstance().create(IBirdHotspot::class.java)
}