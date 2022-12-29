package com.week2.chargepig.models

import com.google.gson.annotations.SerializedName

data class TestData(
    @SerializedName("message") val hello : String
)
