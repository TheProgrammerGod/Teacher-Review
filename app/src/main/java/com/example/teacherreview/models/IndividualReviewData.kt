package com.example.teacherreview.models

data class IndividualReviewData (
    val _id : String ,
    val review : String ,
    val rating : RatingData ,
    val faculty : IndividualFacultyData ,
    val subject : SubjectsData ,
//    val createdBy : UserData
    )
// Everything is Serialized according to the JSON response file