package com.example.teacherreview.models


data class ReviewPostData(
    val faculty: String,
    val rating: RatingData,
    val review: String,
    val subject: String
)