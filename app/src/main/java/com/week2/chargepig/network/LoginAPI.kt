package com.week2.chargepig.network

import com.week2.chargepig.network.models.LoginData
import com.week2.chargepig.network.models.ResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("/login")
    fun login(
        @Body params : LoginData
    ): Call<ResponseData>

}