package org.dt.bexon.topics.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Topics (
	@SerializedName("status")
	val status : Int,
	@SerializedName("message")
	val message : String,
	@SerializedName("data")
	val data : List<Data>
): Serializable