package com.example.teacherreview.repository

import com.example.teacherreview.api.RetrofitInstance.apiInstance
import com.example.teacherreview.models.*
import com.example.teacherreview.utils.Constants

// This class basically is responsible for calling whether we want th code
class Repository {

    // This calls the API and posts the User Authentication request
    suspend fun postLoginRequest(postLoginData: PostLoginData) : retrofit2.Response<UserAuthentication>{
        return apiInstance.postLoginRequest(postLoginData)
    }

    // This calls the API and posts the new User Request to the Server
    suspend fun postSignupRequest(postSignupData: PostSignupData) : retrofit2.Response<UserData>{
        return apiInstance.postSignupRequest(postSignupData)
    }

    // This calls the API and fetches all the Teachers there in the Database
    suspend fun getTeacherList(token : String = Constants.TOKEN , limitValue : Int , facultyName : String? = null) :retrofit2.Response<FacultiesData>{
        return apiInstance.getTeacherList(token , limitValue , facultyName)
    }

    // This calls the API and fetches detailed Reviews of a particular Teachers
    suspend fun getDetailedReviews(token : String = Constants.TOKEN , facultyId : String , limitValue: Int) : retrofit2.Response<ReviewData>{
        return apiInstance.getDetailedReviews(token , facultyId , limitValue)
    }

    // This calls the API and fetches particular Student Review History Data
    suspend fun getStudentReviewHistory(token: String = Constants.TOKEN , studentId: String , limitValue: Int) : retrofit2.Response<ReviewData>{
        return apiInstance.getStudentReviewHistory(token , studentId , limitValue)
    }

    // This calls the API and posts a Teacher Review to the Database
    suspend fun postTeacherReview(token : String = Constants.TOKEN , reviewPostData: ReviewPostData) : retrofit2.Response<ReviewPostData>{
        return apiInstance.postTeacherReviews(token , reviewPostData)
    }
}