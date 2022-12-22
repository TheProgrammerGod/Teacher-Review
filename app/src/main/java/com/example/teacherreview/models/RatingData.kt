package com.example.teacherreview.models

import com.google.gson.annotations.SerializedName

data class RatingData(
    @SerializedName("teaching")
    val teachingRating : RatingParameterData? ,
    @SerializedName("marking")
    val markingRating : RatingParameterData? ,
    @SerializedName("attendance")
    val attendanceRating : RatingParameterData? ,
    @SerializedName("overall")
    val overallRating : RatingParameterData?
)