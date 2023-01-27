package com.example.teacherreview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ActivityIntroductionAndWelcomingBinding

class IntroductionAndWelcomingActivity : AppCompatActivity() {

    // Binding Variable
    private lateinit var binding : ActivityIntroductionAndWelcomingBinding
    // NavController variable
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionAndWelcomingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up all the Instances of the Fragment which will be needed throughout the Fragment
        setupInstances()

    }

    // This function setups all the Instances which are needed for this Fragment
    private fun setupInstances(){

        // Navigation Controller Setup
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewIntroductionAndWelcoming) as NavHostFragment
        navController = navHostFragment.navController
    }
}