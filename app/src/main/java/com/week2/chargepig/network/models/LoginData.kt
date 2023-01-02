package com.week2.chargepig.network.models

import com.google.gson.annotations.SerializedName

data class LoginData(

    @SerializedName("userId")val userId : Int,
    @SerializedName("userPwd") val userPwd : String
)
