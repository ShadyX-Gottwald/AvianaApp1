package skosana.avianaapp1.Domain.Models.NotableBirdSightings


import com.google.gson.annotations.SerializedName

data class NotableBirdSightings(
    @SerializedName("comName")
    val comName: String = "",
    @SerializedName("howMany")
    val howMany: Int = 0,
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lng")
    val lng: Double = 0.0,
    @SerializedName("locId")
    val locId: String = "",
    @SerializedName("locName")
    val locName: String = "",
    @SerializedName("locationPrivate")
    val locationPrivate: Boolean = false,
    @SerializedName("obsDt")
    val obsDt: String = "",
    @SerializedName("obsReviewed")
    val obsReviewed: Boolean = false,
    @SerializedName("obsValid")
    val obsValid: Boolean = false,
    @SerializedName("sciName")
    val sciName: String = "",
    @SerializedName("speciesCode")
    val speciesCode: String = "",
    @SerializedName("subId")
    val subId: String = ""
) {
    fun NotableBirdSightings.toMapDisplay() {

    }
}