package skosana.avianaapp1.Features.GoogleMap.GeoCoding.models


import com.google.gson.annotations.SerializedName

data class GeocodeApiModel(
    @SerializedName("plus_code")
    val plusCode: PlusCode,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String
)