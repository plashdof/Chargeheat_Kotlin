package com.week2.chargepig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    val mainurl = "http://15.165.199.71:8080"
    val testurl = "http://13.209.0.238:8080"
    val buildRetro = Retrofit.Builder()
        .baseUrl(testurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance() : Retrofit{
        return buildRetro
    }

}