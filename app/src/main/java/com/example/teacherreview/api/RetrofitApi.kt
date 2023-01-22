package com.example.teacherreview.api

import com.example.teacherreview.models.*
import retrofit2.http.*

// This is the Interface which gives all the functions which ar later implemented by the Retrofit Library itself
interface RetrofitApi {

    // This calls the API and fetches all the Teachers there in the Database
    @GET("faculties")
    suspend fun getTeacherList(
        @Header("Authorization") token: String,
        @Query("${"$"}limit") limitValue: Int ,
        @Query("name[${"$"}search]")facultyName : String?
    ) : retrofit2.Response<FacultiesData>

    // This calls the API and fetches detailed Reviews of a Teacher and all about him in the database
    @GET("reviews?${"$"}populate=faculty&${"$"}populate=subject&${"$"}populate=createdBy")
    suspend fun getDetailedReviews(
        @Header("Authorization") token: String ,
        @Query("faculty") facultyId : String ,
        @Query("${"$"}limit") limitValue: Int
    ) : retrofit2.Response<ReviewData>

    // This calls the API and fetches the particular Student Review History
    @GET("reviews?${"$"}populate=faculty&${"$"}populate=subject&${"$"}populate=createdBy")
    suspend fun getStudentReviewHistory(
        @Header("Authorization") token: String ,
        @Query("createdBy")studentId : String ,
        @Query("${"$"}limit") limitValue : Int
    ) : retrofit2.Response<ReviewData>

    // This calls the API and posts the Review Data to the Database
    @POST("reviews")
    suspend fun postTeacherReviews(
        @Header("Authorization") token: String ,
        @Body post : ReviewPostData
    ) : retrofit2.Response<ReviewPostData>
}

//TODO :- We need to make the function in such a way that it takes the token of each user by itself and we don't have to do manually