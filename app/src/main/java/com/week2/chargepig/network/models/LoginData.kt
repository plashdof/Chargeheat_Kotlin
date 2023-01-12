package com.week2.chargepig.network.models

import com.google.gson.annotations.SerializedName

data class LoginData(

    @SerializedName("id")val id : String,
    @SerializedName("pw") val pw : String
)
