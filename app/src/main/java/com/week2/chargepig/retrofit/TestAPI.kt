package com.week2.chargepig.retrofit

import com.week2.chargepig.models.TestData
import retrofit2.Call
import retrofit2.http.GET

interface TestAPI {

    @GET("/")
    fun helloworld(
    ): Call<TestData>
}