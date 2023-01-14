package com.example.teacherreview.models


data class IndividualFacultyData(
    val _id : String ,
    val name : String = "" ,
    val avgRating : Double = 0.0 ,
    val code : String? = null
)