package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class FacultiesData(
    @SerializedName("total")
    val totalFaculties: Int ,
    @SerializedName("limit")
    val fetchLimit : Int ,
    @SerializedName("skip")
    val fetchSkip : Int ,
    @SerializedName("data")
    val individualFacultyData : List<IndividualFacultyData>
)