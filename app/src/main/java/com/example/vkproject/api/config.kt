package com.example.secondfbtect.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getAPI() = getRetrofit().create(API::class.java)

fun getRetrofit() : Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.vk.com/method/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}