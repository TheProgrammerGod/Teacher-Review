package com.example.teacherreview.models

data class IndividualReviewData (
    val _id : String = "" ,
    val review : String? = null ,
    val rating : RatingData? = null ,
    val faculty : IndividualFacultyData ,
    val subject : SubjectsData ,
//    val createdBy : UserData
    )
// Everything is Serialized according to the JSON response file