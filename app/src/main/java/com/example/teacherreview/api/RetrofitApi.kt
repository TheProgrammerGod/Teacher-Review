package com.example.teacherreview.api

import com.example.teacherreview.models.FacultiesData
import com.example.teacherreview.models.ReviewData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// This is the Interface which gives all the functions which ar later implemented by the Retrofit Library itself
interface RetrofitApi {

    // This calls the API and fetches all the Teachers there in the Database
    @GET("faculties")
    suspend fun getTeacherList(@Header("Authorization") token: String) : retrofit2.Response<FacultiesData>

    // This calls the API and fetches detailed Reviews of a Teacher and all about him in the database
    @GET("reviews?${"$"}populate=faculty&${"$"}populate=subject")
    suspend fun getDetailedReviews(
        @Header("Authorization") token: String ,
        @Query("faculty") facultyId : String
    ) : retrofit2.Response<ReviewData>
}

//TODO :- We need to make the function in such a way that it takes the token of each user by itself and we don't have to do manually