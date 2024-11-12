package skosana.avianaapp1.Features.Core.remote

import io.ktor.client.HttpClient

import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.HttpTimeout

import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
//import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skosana.avianaapp1.Features.Core.remote.constants.EbirdRoutes
import skosana.avianaapp1.Features.Core.remote.services.IBirdHotspot
import skosana.avianaapp1.Features.Core.remote.services.IWeatherApi


object EbirdApiClient {

   // private const val baseUrl = //"https://api.weatherapi.com"
      //  EbirdRoutes.BLOG_POST
    const  val api_key = "2b33f22a4df849288e535557241808"
    const val Key = "X-eBirdApiToken"
    const val Value = "rhj2pqdjsgpu"

//    private fun getInstance(): Retrofit {
//        return  Retrofit.Builder()
//            .baseUrl(EbirdRoutes.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    }

    private fun getHotspotInstance(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(EbirdRoutes.EBIRD_HOTSPOTS_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }


   // val PostService : IWeatherApi = getInstance().create(IWeatherApi::class.java)
    val HotspotService : IBirdHotspot = getHotspotInstance().create(IBirdHotspot::class.java)

}