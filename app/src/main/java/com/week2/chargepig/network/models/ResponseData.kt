package com.week2.chargepig.network.models

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("code") val code : Int,
    @SerializedName("message") val msg : String
)
