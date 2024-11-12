package skosana.avianaapp1.Features.Core.remote.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import skosana.avianaapp1.Features.GoogleMap.GeoCoding.models.GeocodeApiModel

interface IGeocodeApi {

    @GET("/maps/api/geocode/json")
    suspend fun GeocodeLatLongToAddress(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String
    ): Response<GeocodeApiModel>
}