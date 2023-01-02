package com.example.teacherreview.models

data class ReviewData(
    val _id : String ,
    val review : String? ,
    val rating : RatingData? ,
    val subject : SubjectsData? ,
    val faculty : IndividualFacultyData
)
