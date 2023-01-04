package com.example.teacherreview.api

import com.example.teacherreview.models.FacultiesData
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitApi {

    // This calls the API and fetches all the Teachers there in the Database
    @GET("faculties")
    suspend fun getTeacherList(@Header("Authorization") token: String) : retrofit2.Response<FacultiesData>
}