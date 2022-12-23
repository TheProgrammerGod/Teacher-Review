package com.example.teacherreview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherreview.models.ReviewData
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel : ViewModel() {
    private val _myTeacherList : MutableLiveData<Response<ReviewData>> = MutableLiveData()
    val myTeacherList : LiveData<Response<ReviewData>>
        get() = _myTeacherList

    init{
        // TODO :- Make a repository Instance and calls the API once to fetch the data
    }

    // Function calls repository and fetches data from API
    fun getTeacherReviewList(){
        viewModelScope.launch {
            // TODO :- Ask for the Repository to fetch the Data
        }
    }

    // Function calls repository and fetches data from API according to the subjects
    fun getTeacherReviewListBySubject(subject : String){
        viewModelScope.launch {
            // TODO :- Ask the Repository to fetch the data by the @QUERY of (subject)
        }
    }

    // Function calls teh repository and fetches data in ascending order of
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
}