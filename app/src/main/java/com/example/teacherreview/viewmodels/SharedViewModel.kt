package com.example.teacherreview.viewmodels

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherreview.models.FacultiesData
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel : ViewModel() {
    private val _myTeacherList : MutableLiveData<Response<FacultiesData>> = MutableLiveData()
    val myTeacherList : LiveData<Response<FacultiesData>>
        get() = _myTeacherList

    private var myRepository = Repository()

    private val _myDetailedReviewList : MutableLiveData<Response<ReviewData>> = MutableLiveData()
    val myDetailedReviewList : LiveData<Response<ReviewData>>
        get() = _myDetailedReviewList

    // Function calls repository and fetches data from API
    fun getTeacherList(){
        viewModelScope.launch {
            val response = myRepository.getTeacherList()
            _myTeacherList.value = response
        }
    }

    // This calls the API and fetches detailed Reviews of a Teachers
    fun getDetailedReviews(facultyId : String){
        viewModelScope.launch {
            val response = myRepository.getDetailedReviews(facultyId = facultyId)
            _myDetailedReviewList.value = response
        }
    }

    // Function calls repository and fetches data from API according to the subjects
    fun getTeacherReviewListBySubject(subject : String){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch the data by the @QUERY of (subject)
        }
    }

    // Function calls the repository and fetches data in ascending order of
    fun getTeachersReviewListInDescending(){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch the data in sorted Order
        }
    }

    // Function calls the repository and fetches reviews given by The Student
    fun getStudentReviewList(roll : Int){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch data regarding student reviews
        }
    }

    //Function calls repository and fetches the details of the Student
    fun getStudentDetail(){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch the student detail
        }
    }
}