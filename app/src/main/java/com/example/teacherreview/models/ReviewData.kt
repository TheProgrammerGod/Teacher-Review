package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class ReviewData(
    var avgTeachingRating : Double = 0.0 ,
    var avgMarkingRating : Double = 0.0 ,
    var avgAttendanceRating : Double = 0.0 ,
    val total : Int = 0 ,
    val limit : Int = 0 ,
    val skip : Int = 0 ,
    @SerializedName("data")
    val individualReviewData : List<IndividualReviewData>? = null
)
