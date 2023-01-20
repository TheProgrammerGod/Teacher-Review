package com.example.teacherreview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherreview.models.FacultiesData
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.models.ReviewPostData
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

    // Student Review Post Request
    private var _reviewPostSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val reviewPostSuccess : LiveData<Boolean>
        get() = _reviewPostSuccess

    // Faculty Id is stored here
    var facultyId : String? = null

    // This is the limit of the maximum number of elements that should be fetched from the server
    var studentReviewHistoryLimit = 0

    //This is the limit of the maximum number of teachers we can fetch from the server
    var teacherListLimit = 0

    //This is the limit of the maximum number of teacher Review Details we can fetch from Server
    var teacherDetailReviewLimit = 0

    // Function calls repository and fetches data from API
    fun getTeacherList(){
        viewModelScope.launch {
            val response = myRepository.getTeacherList(limitValue = teacherListLimit)
            _teacherList.value = response
        }
    }

    // This calls the API and fetches detailed Reviews of a Teachers
    fun getDetailedReviews(){
        viewModelScope.launch {
            val response = myRepository.getDetailedReviews(facultyId = facultyId!! , limitValue = teacherDetailReviewLimit)
            _detailedReviewList.value = response
        }
    }

    // Function calls the repository and fetches reviews given by The Student
    fun getStudentReviewList(studentId : String){
        viewModelScope.launch {
            val response = myRepository.getStudentReviewHistory(studentId = studentId , limitValue = studentReviewHistoryLimit)
            _studentReviewHistoryList.value = response
        }
    }

    //Function calls repository and fetches the details of the Student
    fun getStudentDetail(){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch the student detail
        }
    }

    // Function which calls the Repository and posts the Review
    fun postTeacherReview(reviewPostData: ReviewPostData){
        viewModelScope.launch {
            val response = myRepository.postTeacherReview(reviewPostData = reviewPostData)
            _reviewPostSuccess.value = response.isSuccessful
        }
    }

    // Function to set the reviewPostSuccess variable back to false
    fun setReviewPostSuccess(){
        _reviewPostSuccess = MutableLiveData()
    }

    // Function which sets the Average Ratings for showing those in the RecyclerView and for Testing Purposes----------------------
    fun setTeacherAverageRatings(response : ReviewData) : ReviewData{
        val individualReviewData = response.individualReviewData
        var teachingPoint = 0.0
        var teachingTotal = 0
        var attendancePoint = 0.0
        var attendanceTotal = 0
        var markingPoint = 0.0
        var markingTotal = 0
        for(element in individualReviewData!!){
            var overallPoint = 0.0
            var overallTotal = 0
            if(element.rating?.teachingRating?.ratedPoints != null) {
                teachingPoint += element.rating.teachingRating.ratedPoints
                teachingTotal++
                overallTotal++
                overallPoint += element.rating.teachingRating.ratedPoints
            }
            if(element.rating?.markingRating?.ratedPoints != null){
                markingTotal++
                markingPoint+= element.rating.markingRating.ratedPoints
                overallTotal++
                overallPoint += element.rating.markingRating.ratedPoints
            }
            if(element.rating?.attendanceRating?.ratedPoints != null){
                attendancePoint+= element.rating.attendanceRating.ratedPoints
                attendanceTotal ++
                overallTotal++
                overallPoint += element.rating.attendanceRating.ratedPoints
            }
            element.rating?.overallRating = overallPoint/overallTotal
        }
        response.avgAttendanceRating = attendancePoint/attendanceTotal
        response.avgMarkingRating = markingPoint/markingTotal
        response.avgTeachingRating = teachingPoint/teachingTotal
        return response
    }

    // This function sets the Faculty Id
    fun setFacultyID(facultyId: String){
        this.facultyId = facultyId
    }
}