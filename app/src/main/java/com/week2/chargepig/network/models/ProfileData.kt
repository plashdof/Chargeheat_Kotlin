package com.week2.chargepig.network.models

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("pw") val pw : String,
    @SerializedName("point") val point : Int,
    @SerializedName("rentalnum") val rentalnum : Int,
    @SerializedName("dong") val dong : Int,
    @SerializedName("hosu") val hosu : Int,
    @SerializedName("role") val role : String
)
