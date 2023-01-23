package com.example.teacherreview.models


data class IndividualFacultyData(
    val _id : String,
    val name : String = "",
    var avgRating : Double = 0.0,
    val avgTeachingRating : Double = 0.0,
    val avgMarkingRating : Double = 0.0,
    val avgAttendanceRating : Double = 0.0,
    val code : String? = null
)