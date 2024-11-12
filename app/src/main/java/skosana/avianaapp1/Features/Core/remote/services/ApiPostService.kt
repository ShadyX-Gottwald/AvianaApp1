package skosana.avianaapp1.Features.Core.remote.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import skosana.avianaapp1.Features.Core.remote.models.Post


interface IWeatherApi {

    @GET("/posts")
    suspend fun getPosts(
                         //  @Query("q") city: String
    ): Response<List<Post>>

}

