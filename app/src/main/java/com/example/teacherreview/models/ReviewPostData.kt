package com.example.teacherreview.models


data class ReviewPostData(
    val faculty: String ,
    val rating: RatingData? = null ,
    val review: String? = null,
    val subject: String
)