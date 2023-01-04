package com.example.teacherreview.repository

import com.example.teacherreview.api.RetrofitInstance.apiInstance
import com.example.teacherreview.models.FacultiesData
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.utils.Constants

// This class basically is responsible for calling whether we want th code
class Repository {

    // This calls the API and fetches all the Teachers there in the Database
    suspend fun getTeacherList(token : String = Constants.TOKEN) :retrofit2.Response<FacultiesData>{
        return apiInstance.getTeacherList(token)
    }

    // This calls the API and fetches detailed Reviews of a Teachers
    suspend fun getDetailedReviews(token : String = Constants.TOKEN , facultyId : String) : retrofit2.Response<ReviewData>{
        return apiInstance.getDetailedReviews(token , facultyId)
    }
}