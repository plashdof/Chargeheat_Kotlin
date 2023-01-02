package com.week2.chargepig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    val buildLikeRetro = Retrofit.Builder()
        .baseUrl("http://13.209.0.238:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}