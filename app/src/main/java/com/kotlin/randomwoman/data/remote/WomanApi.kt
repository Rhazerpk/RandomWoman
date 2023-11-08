package com.kotlin.randomwoman.data.remote

import com.kotlin.randomwoman.data.remote.dto.results
import retrofit2.http.GET

interface WomanApi {
    @GET("api/?gender=female&?page=3&results=10&seed=abc")
    suspend fun getWomen(): results
}