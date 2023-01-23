package com.example.teacherreview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherreview.models.PostSignupData
import com.example.teacherreview.models.UserData
import com.example.teacherreview.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SignupActivityViewModel : ViewModel(){

    // Repository variable of the viewModel
    private val myRepository = Repository()

    // Observable which is observed in the Sign Up Activity
    private val _userSignupResponse : MutableLiveData<Response<UserData>> = MutableLiveData()
    val userSignupData : LiveData<Response<UserData>>
        get() = _userSignupResponse

    // This function post the new User request to the Server
    fun postSignupRequest(postSignupData: PostSignupData) {
        viewModelScope.launch {
            val response = myRepository.postSignupRequest(postSignupData)
            _userSignupResponse.value = response
        }
    }
}