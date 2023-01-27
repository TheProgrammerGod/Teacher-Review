package com.example.teacherreview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// This is the Sign up Activity View model Factory which makes the View model of that class and returns it
class RegisterScreenViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterScreenViewModel() as T
    }
}