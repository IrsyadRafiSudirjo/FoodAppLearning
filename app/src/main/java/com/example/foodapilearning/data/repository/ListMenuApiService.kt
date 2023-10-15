package com.example.foodapilearning.data.repository

import com.example.foodapilearning.data.response.ListMenuResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //membuat interface call api dari retrofit
    @GET("filter.php?")
    fun getMenu(@Query("a") place:String): Call<ListMenuResponse>
}