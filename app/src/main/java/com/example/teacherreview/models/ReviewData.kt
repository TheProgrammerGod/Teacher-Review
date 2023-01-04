package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class ReviewData(
    val total : Int ,
    val limit : Int ,
    val skip : Int ,
    @SerializedName("data")
    val individualReviewData : List<IndividualReviewData>
)
