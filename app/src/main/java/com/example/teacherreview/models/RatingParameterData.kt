package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class RatingParameterData(
    @SerializedName("points")
    val ratedPoints : Double? = null ,
    val description : String? = null
)