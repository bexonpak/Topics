package org.dt.bexon.topics.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopicData (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("extra")
    val extra: String,
    @SerializedName("link")
    val link: String
): Serializable