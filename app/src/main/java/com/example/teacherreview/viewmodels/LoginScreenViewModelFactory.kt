package com.example.teacherreview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// This is the Login Activity View Model Provider
class LoginScreenViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginScreenViewModel() as T
    }
}