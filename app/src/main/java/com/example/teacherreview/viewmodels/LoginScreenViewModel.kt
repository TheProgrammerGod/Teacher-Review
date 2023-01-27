package com.example.teacherreview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherreview.models.PostLoginData
import com.example.teacherreview.models.UserAuthentication
import com.example.teacherreview.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginScreenViewModel() : ViewModel() {

//    private lateinit var signInOptions: GoogleSignInOptions
//    private var signInRequest: BeginSignInRequest

    // Repository Variable
    private val myRepository = Repository()

    // Mutable Live Data which is observed from the Login Activity
    private val _userLoginResponse : MutableLiveData<Response<UserAuthentication>> = MutableLiveData()
    val userLoginResponse : LiveData<Response<UserAuthentication>>
        get() = _userLoginResponse

//    init {
//        signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(
//                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(Resources.getSystem().getString(R.string.web_client_id))
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(true)
//                    .build())
//            .build()
//    }

    // This function posts the Login Request to the API
    fun postLoginRequest(postLoginData: PostLoginData){
        viewModelScope.launch {
            val response = myRepository.postLoginRequest(postLoginData)
            _userLoginResponse.value = response
        }
    }
}