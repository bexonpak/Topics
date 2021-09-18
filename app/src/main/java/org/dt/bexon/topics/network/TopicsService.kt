package org.dt.bexon.topics.network

import org.dt.bexon.topics.model.Topics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TopicsService {

    @GET("api/hot/list")
    fun getList(@Query("type") type: Int) : Call<Topics>
}