package com.example.teacherreview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherreview.models.FacultiesData
import com.example.teacherreview.models.RatingData
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel : ViewModel() {

    // Repository Instance
    private var myRepository = Repository()

    // Teacher List Fragment Observable Variables
    private val _teacherList : MutableLiveData<Response<FacultiesData>> = MutableLiveData()
    val teacherList : LiveData<Response<FacultiesData>>
        get() = _teacherList

    // Teacher Detailed Review Fragment Observable Variables
    private val _detailedReviewList : MutableLiveData<Response<ReviewData>> = MutableLiveData()
    val detailedReviewList : LiveData<Response<ReviewData>>
        get() = _detailedReviewList

    // Student Review History Fragment Observable Variables
    private val _studentReviewHistoryList : MutableLiveData<Response<ReviewData>> = MutableLiveData()
    val studentReviewHistoryList : LiveData<Response<ReviewData>>
        get() = _studentReviewHistoryList

    // Function calls repository and fetches data from API
    fun getTeacherList(){
        viewModelScope.launch {
            val response = myRepository.getTeacherList()
            _teacherList.value = response
        }
    }

    // This calls the API and fetches detailed Reviews of a Teachers
    fun getDetailedReviews(facultyId : String){
        viewModelScope.launch {
            val response = myRepository.getDetailedReviews(facultyId = facultyId)
            _detailedReviewList.value = response
        }
    }

    // Function calls repository and fetches data from API according to the subjects
    fun getTeacherReviewListBySubject(subject : String){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch the data by the @QUERY of (subject)
        }
    }

    // Function calls the repository and fetches reviews given by The Student
    fun getStudentReviewList(studentId : String){
        viewModelScope.launch {
            val response = myRepository.getStudentReviewHistory(studentId = studentId)
            _studentReviewHistoryList.value = response
        }
    }

    //Function calls repository and fetches the details of the Student
    fun getStudentDetail(){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch the student detail
        }
    }


    fun setTeacherAverageRatings(response : ReviewData) : ReviewData{
        val individualReviewData = response.individualReviewData
        var teachingPoint = 0.0
        var teachingTotal = 0
        var attendancePoint = 0.0
        var attendanceTotal = 0
        var markingPoint = 0.0
        var markingTotal = 0
        for(element in individualReviewData){
            var overallPoint = 0.0
            var overallTotal = 0
            if(element.rating.teachingRating?.ratedPoints != null) {
                teachingPoint += element.rating.teachingRating.ratedPoints
                teachingTotal++
                overallTotal++
                overallPoint += element.rating.teachingRating.ratedPoints
            }
            if(element.rating.markingRating?.ratedPoints != null){
                markingTotal++
                markingPoint+= element.rating.markingRating.ratedPoints
                overallTotal++
                overallPoint += element.rating.markingRating.ratedPoints
            }
            if(element.rating.attendanceRating?.ratedPoints != null){
                attendancePoint+= element.rating.attendanceRating.ratedPoints
                attendanceTotal ++
                overallTotal++
                overallPoint += element.rating.attendanceRating.ratedPoints
            }
            element.rating.overallRating = overallPoint/overallTotal
        }
        response.avgAttendanceRating = attendancePoint/attendanceTotal
        response.avgMarkingRating = markingPoint/markingTotal
        response.avgTeachingRating = teachingPoint/teachingTotal
        return response
    }



}