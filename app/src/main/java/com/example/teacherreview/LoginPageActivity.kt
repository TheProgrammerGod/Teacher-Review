package com.example.teacherreview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teacherreview.databinding.ActivityLoginPageBinding


class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)






        //Testing for checking if Fragments are Working Properly
        val intent = Intent(this,HomePageActivity::class.java)

        //Starting the next Activity
        startActivity(intent)
        //Finishing the Current Activity so that it wont be shown if the User hits back
        finish()
    }
}