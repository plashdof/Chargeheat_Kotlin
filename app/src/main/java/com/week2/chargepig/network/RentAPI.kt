package com.week2.chargepig.network

import com.week2.chargepig.network.models.RentData
import com.week2.chargepig.network.models.ResponseData
import com.week2.chargepig.network.models.SignupData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RentAPI {

    @POST("/user/join")
    fun rent(
        @Header("id") id : String? = "",
        @Body params : RentData
    ): Call<ResponseData>
}