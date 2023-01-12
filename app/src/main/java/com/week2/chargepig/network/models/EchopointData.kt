package com.week2.chargepig.network.models

import com.google.gson.annotations.SerializedName

data class EchopointData(
    @SerializedName("name") val name : String,
    @SerializedName("image") val image : String
)
