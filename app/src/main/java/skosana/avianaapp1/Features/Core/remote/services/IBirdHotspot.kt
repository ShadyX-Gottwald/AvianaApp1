package skosana.avianaapp1.Features.Core.remote.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query
import skosana.avianaapp1.Domain.Models.BirdWithTaxonomy.TaxonomicBird
import skosana.avianaapp1.Domain.Models.Hotspots.BirdHotspot
import skosana.avianaapp1.Domain.Models.NotableBirdSightings.NotableBirdSightings
import skosana.avianaapp1.Features.Core.remote.models.Post

interface IBirdHotspot {

    @GET("/v2/ref/hotspot/geo?&fmt=json")
    suspend fun getHotspotLocations(
        @HeaderMap ebirdKey: Map<String, String> ,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
    ): Response<List<BirdHotspot>>

    @GET("/v2/ref/taxonomy/ebird?=&fmt=json")
    suspend fun getDefaultTaxonomicBirds(
        @HeaderMap ebirdKey: Map<String, String> ,
        @Query("locale") locale: String
    ): Response<List<TaxonomicBird>>

    @GET("v2/data/obs/geo/recent/notable")
    suspend fun getNotableBirdSightings(
        @HeaderMap ebirdKey: Map<String, String> ,
        @Query("lat") laat: Double,
        @Query("lng") lng: Double,
        @Query("maxResults") maxResults: Int,
        @Query("dist") dist: Int = 50,
        @Query("sppLocale") sppLocale: String,
        ): Response<List<NotableBirdSightings>>


}