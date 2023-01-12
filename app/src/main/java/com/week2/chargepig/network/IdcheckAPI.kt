package com.week2.chargepig.network

import com.week2.chargepig.network.models.IdcheckData
import com.week2.chargepig.network.models.ResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IdcheckAPI {
    @POST("/user/join/checkid")
    fun idcheck(
        @Body params: IdcheckData
    ) : Call<ResponseData>
}