package com.week2.chargepig.network.models

import com.google.gson.annotations.SerializedName

data class SignupData(
    @SerializedName("ID") val ID : String,
    @SerializedName("PW") val PW : String,
    @SerializedName("name") val name : String,
    @SerializedName("dong") val dong : Int,
    @SerializedName("hosu") val hosu : Int
)
