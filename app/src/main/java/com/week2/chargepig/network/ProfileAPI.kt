package com.week2.chargepig.network

import com.week2.chargepig.network.models.ProfileData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileAPI {

    @GET("/user/profile")
    fun getprofile(
        @Header("id") id : String? = ""
    ): Call<ProfileData>
}