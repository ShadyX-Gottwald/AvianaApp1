package skosana.avianaapp1.Domain.Models.Hotspots


import com.google.gson.annotations.SerializedName



data class BirdHotspot(
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("latestObsDt")
    val latestObsDt: String,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("locId")
    val locId: String,
    @SerializedName("locName")
    val locName: String,
    @SerializedName("numSpeciesAllTime")
    val numSpeciesAllTime: Int,
    @SerializedName("subnational1Code")
    val subnational1Code: String
)