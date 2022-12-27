package com.example.teacherreview.viewmodels


import android.content.res.Resources
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.ViewModel
import com.example.teacherreview.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class LoginPageViewModel : ViewModel() {

    private lateinit var signInOptions: GoogleSignInOptions
    private var signInRequest: BeginSignInRequest

    init {
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(Resources.getSystem().getString(R.string.web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()
    }

}