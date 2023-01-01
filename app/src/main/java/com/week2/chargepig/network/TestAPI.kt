package com.week2.chargepig.network

import com.week2.chargepig.network.models.TestData
import retrofit2.Call
import retrofit2.http.GET

interface TestAPI {

    @GET("/")
    fun helloworld(
    ): Call<TestData>
}