package com.week2.chargepig.network

import com.week2.chargepig.network.models.LoginData
import com.week2.chargepig.network.models.ResponseData
import com.week2.chargepig.network.models.SignupData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupAPI {

    @POST("/signup")
    fun signup(
        @Body params : SignupData
    ): Call<ResponseData>

}