package com.week2.chargepig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    val buildLikeRetro = Retrofit.Builder()
        .baseUrl("http://54.65.19.20:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}