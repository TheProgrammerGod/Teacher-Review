package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class RatingData(
    var overallRating : Double = 0.0,
    @SerializedName("teaching")
    val teachingRating : RatingParameterData? = null,
    @SerializedName("marking")
    val markingRating : RatingParameterData? = null ,
    @SerializedName("attendance")
    val attendanceRating : RatingParameterData? = null
)