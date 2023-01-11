package com.week2.chargepig.network

import com.week2.chargepig.network.models.EchopointData
import com.week2.chargepig.network.models.LoginData
import com.week2.chargepig.network.models.ResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EchopointAPI {

    @POST("/echopoint")
    fun echopoint(
        @Body params : EchopointData
    ): Call<ResponseData>
}