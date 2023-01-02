package com.example.teacherreview.api

import com.example.teacherreview.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Contains the Base Url which gives all the details from the Database
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Variable which can be used to call all the functions of the RetrofitApi interface
    val apiInstance : RetrofitApi by lazy{
        retrofit.create(RetrofitApi::class.java)
    }
}