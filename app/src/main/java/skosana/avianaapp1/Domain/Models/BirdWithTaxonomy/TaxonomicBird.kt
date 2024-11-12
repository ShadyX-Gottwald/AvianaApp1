package skosana.avianaapp1.Domain.Models.BirdWithTaxonomy


import com.google.gson.annotations.SerializedName

data class TaxonomicBird(
    @SerializedName("bandingCodes")
    val bandingCodes: List<Any> = listOf(),
    @SerializedName("category")
    val category: String = "",
    @SerializedName("comName")
    val comName: String = "",
    @SerializedName("comNameCodes")
    val comNameCodes: List<String> = listOf(),
    @SerializedName("familyCode")
    val familyCode: String = "",
    @SerializedName("familyComName")
    val familyComName: String = "",
    @SerializedName("familySciName")
    val familySciName: String = "",
    @SerializedName("order")
    val order: String = "",
    @SerializedName("sciName")
    val sciName: String = "",
    @SerializedName("sciNameCodes")
    val sciNameCodes: List<String> = listOf(),
    @SerializedName("speciesCode")
    val speciesCode: String = "",
    @SerializedName("taxonOrder")
    val taxonOrder: Double = 0.0
)