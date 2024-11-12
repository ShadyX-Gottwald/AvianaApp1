package skosana.avianaapp1.Features.Core.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import androidx.annotation.Keep


@Serializable
data class Post(
    @SerialName("body")
    val body: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int
) {
    override fun toString(): String {
        return "$title : $id"
    }
}