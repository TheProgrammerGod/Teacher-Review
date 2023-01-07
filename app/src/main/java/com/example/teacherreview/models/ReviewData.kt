package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class ReviewData(
    var avgTeachingRating : Double ,
    var avgAttendanceRating : Double ,
    var avgMarkingRating : Double ,
    val total : Int ,
    val limit : Int ,
    val skip : Int ,
    @SerializedName("data")
    val individualReviewData : List<IndividualReviewData>
)
