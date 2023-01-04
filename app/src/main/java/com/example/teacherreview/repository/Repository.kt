package com.example.teacherreview.repository

import com.example.teacherreview.api.RetrofitInstance
import com.example.teacherreview.models.FacultiesData
import com.example.teacherreview.utils.Constants

class Repository {

    // This calls the API and fetches all the Teachers there in the Database
    suspend fun getTeacherList(token : String = Constants.TOKEN) :retrofit2.Response<FacultiesData>{
        return RetrofitInstance.apiInstance.getTeacherList(token)
    }
}