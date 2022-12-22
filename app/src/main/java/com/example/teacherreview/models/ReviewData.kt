package com.example.teacherreview.models

data class ReviewData(
    val review : String? ,
    val rating : RatingData? ,
    val subject : SubjectsData? ,
    val faculty : FacultiesData
)
