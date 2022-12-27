package com.example.teacherreview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teacherreview.databinding.ActivityLoginPageBinding
import com.example.teacherreview.viewmodels.LoginPageViewModel


class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginPageBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //Accessing Login Page View Model
//        viewModel = ViewModelProvider(this)[LoginPageViewModel::class.java]

        //Testing for checking if Fragments are Working Properly
        val intent = Intent(this, HomePageActivity::class.java)

        //Starting the next Activity
        startActivity(intent)
        //Finishing the Current Activity so that it wont be shown if the User hits back
        finish()
    }
}