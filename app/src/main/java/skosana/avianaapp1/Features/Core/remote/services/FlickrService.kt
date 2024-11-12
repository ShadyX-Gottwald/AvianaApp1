package skosana.avianaapp1.Features.Core.remote.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import skosana.avianaapp1.Domain.Models.FlickrResponse.FlickrResponse
import skosana.avianaapp1.Features.GoogleMap.GeoCoding.models.GeocodeApiModel

interface FlickrService {
    @GET("?method=flickr.photos.search&format=json&api_key=bc3372a01f95e6efcb1c98068b721fb0")
    suspend fun GetImagesOfSubject(
        @Query("tag") tag: String,

    ): Response<List<FlickrResponse>>
}