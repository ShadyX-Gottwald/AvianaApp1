package skosana.avianaapp1.Domain.Models.FlickrResponse


import com.google.gson.annotations.SerializedName

data class FlickrResponse(
    @SerializedName("farm")
    val farm: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("isfamily")
    val isfamily: Int = 0,
    @SerializedName("isfriend")
    val isfriend: Int = 0,
    @SerializedName("ispublic")
    val ispublic: Int = 0,
    @SerializedName("owner")
    val owner: String = "",
    @SerializedName("secret")
    val secret: String = "",
    @SerializedName("server")
    val server: String = "",
    @SerializedName("title")
    val title: String = ""
)