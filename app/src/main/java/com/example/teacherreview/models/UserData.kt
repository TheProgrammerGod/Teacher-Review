package com.example.teacherreview.models

data class UserData(
    val _id : String = "" ,
    val email : String = "" ,
    val name : String = "",
    val role : Int = 0,
    val status : Int = 0
)
// Everything is Serialized according to the JSON response file