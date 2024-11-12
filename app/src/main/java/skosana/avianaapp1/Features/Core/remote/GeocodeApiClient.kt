package skosana.avianaapp1.Features.Core.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skosana.avianaapp1.Features.Core.remote.constants.EbirdRoutes
import skosana.avianaapp1.Features.Core.remote.constants.GeocodeRoutes
import skosana.avianaapp1.Features.Core.remote.services.IGeocodeApi
import skosana.avianaapp1.Features.Core.remote.services.IWeatherApi

object GeocodeApiClient {

    private fun getInstance(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(GeocodeRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val GeocodeService : IGeocodeApi = getInstance().create(IGeocodeApi::class.java)

}