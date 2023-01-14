package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class FacultiesData(
    @SerializedName("total")
    val totalFaculties: Int = 0,
    @SerializedName("limit")
    val fetchLimit : Int = 0 ,
    @SerializedName("skip")
    val fetchSkip : Int = 0 ,
    @SerializedName("data")
    val individualFacultyData : List<IndividualFacultyData>? = null
)