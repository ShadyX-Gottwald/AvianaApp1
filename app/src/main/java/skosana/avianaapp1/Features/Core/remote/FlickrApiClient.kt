package skosana.avianaapp1.Features.Core.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skosana.avianaapp1.Features.Core.remote.constants.EbirdRoutes
import skosana.avianaapp1.Features.Core.remote.constants.FlickRApiRoutes
import skosana.avianaapp1.Features.Core.remote.services.FlickrService

object FlickrApiClient {
     fun GetSubjectImagesUrl(completeFlickrUrl: String): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(FlickRApiRoutes.FLICKR_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val flickrService : FlickrService = GetSubjectImagesUrl(FlickRApiRoutes.FLICKR_BASE_URL).create(FlickrService::class.java)

}