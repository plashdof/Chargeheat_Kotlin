package com.week2.chargepig.network

import com.week2.chargepig.network.models.ResponseData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface EchopointAPI {

    @Multipart
    @POST("/eco")
    fun echopoint(
        @Header("id") id : String? = "",
        @Part photo: MultipartBody.Part,
        @Part("name") name : String,
    ): Call<ResponseData>
}