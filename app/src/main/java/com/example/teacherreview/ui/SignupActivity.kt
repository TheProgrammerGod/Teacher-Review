package com.example.teacherreview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.teacherreview.databinding.ActivitySignupBinding
import com.example.teacherreview.models.PostSignupData
import com.example.teacherreview.viewmodels.*

class SignupActivity : AppCompatActivity() {

    // This is the binding Variable
    private lateinit var binding : ActivitySignupBinding
    // This is the viewModel variable
    private lateinit var myViewModel : SignupActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Calling the setup instances function which sets all the instances and objects for the Activity
        setupInstances()

        // Setting on click listener to the Signup button which posts new user Post request
        binding.btnSignup.setOnClickListener {

            // Checking if the edit Text Fields are empty
            if (binding.etEmailSignUp.text.toString().isEmpty() || binding.etTextPasswordSignUp.text.toString().isEmpty())
                Toast.makeText(this, "Empty Details please Fill the Form again!!", Toast.LENGTH_SHORT).show()
            else {

                // Creating the object which will be the body of the post Request
                val postSignupData = PostSignupData(
                    name = binding.etName.text.toString() ,
                    email = binding.etEmailSignUp.text.toString().trim(),
                    password = binding.etTextPasswordSignUp.text.toString().trim()
                )

                // Calling the Api to make a new user
                myViewModel.postSignupRequest(postSignupData)
            }
        }

        myViewModel.userSignupData.observe(this){response ->

            // If the response is successful then we direct the user to the login Activity
            if(response.isSuccessful){
                Toast.makeText(this , "User Created" , Toast.LENGTH_SHORT).show()

                //Changing the Activity from this to the Login Activity
                val intent = Intent(this, LoginPageActivity::class.java)
                startActivity(intent)

                //Finishing the Current Activity so that it wont be shown if the User hits back
                finish()
            }
            else
                Toast.makeText(this , "Failed to Create" , Toast.LENGTH_SHORT).show()
        }
    }

    // This function setups all the Necessary Instances required in this activity
    private fun setupInstances(){

        //Accessing Login Page View Model
        myViewModel = ViewModelProvider(this , SignupActivityViewModelFactory())[SignupActivityViewModel::class.java]
    }
}