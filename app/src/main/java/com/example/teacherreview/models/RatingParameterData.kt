package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class RatingParameterData(
    @SerializedName("points")
    val ratedPoints : Double? ,
    val description : String?
)