package com.week2.chargepig.network.models

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class EchopointData(

    @SerializedName("photo") val photo : Uri,
    @SerializedName("name") val name : String

)
