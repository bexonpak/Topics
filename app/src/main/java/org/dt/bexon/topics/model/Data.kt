package org.dt.bexon.topics.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Data (
	@SerializedName("id")
	val id : Int,
	@SerializedName("sort")
	val sort : Int,
	@SerializedName("name")
	val name : String,
	@SerializedName("source_source_key")
	val source_source_key : String,
	@SerializedName("icon_color")
	val icon_color : String,
	@SerializedName("data")
	val data : List<TopicData>,
	@SerializedName("create_time")
	val create_time : String
): Serializable