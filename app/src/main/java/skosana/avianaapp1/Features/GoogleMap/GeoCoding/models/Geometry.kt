package skosana.avianaapp1.Features.GoogleMap.GeoCoding.models


import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("bounds")
    val bounds: Bounds,
    @SerializedName("location")
    val location: Location,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("viewport")
    val viewport: Viewport
)