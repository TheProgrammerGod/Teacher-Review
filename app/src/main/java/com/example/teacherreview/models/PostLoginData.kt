package com.example.teacherreview.models

data class PostLoginData(
    val email : String ,
    val password : String ,
    val strategy: String = "local"
)
