package com.example.teacherreview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.teacherreview.databinding.ActivityLoginPageBinding
import com.example.teacherreview.models.PostLoginData
import com.example.teacherreview.utils.Constants
import com.example.teacherreview.viewmodels.LoginPageViewModel
import com.example.teacherreview.viewmodels.LoginPageViewModelFactory

class LoginPageActivity : AppCompatActivity() {

    // Binding Variable of the Layout
    private lateinit var binding : ActivityLoginPageBinding
    // ViewModel variable which sets the ViewModel of this Activity
    private lateinit var myViewModel : LoginPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting up all the Instances
        setupInstances()

        // Setting the on click Listener on the Login Button
        binding.btnLogIn.setOnClickListener {

            // Checking if the edit Text Fields are empty
            if (binding.etEmailLogin.text.toString().isEmpty() || binding.etTextPasswordLogin.text.toString().isEmpty())
                Toast.makeText(this, "Empty Details please Fill the Form again!!", Toast.LENGTH_SHORT).show()
            else {

                // Creating the object which will be the body of the post Request
                val postLoginData = PostLoginData(
                    email = binding.etEmailLogin.text.toString().trim(),
                    password = binding.etTextPasswordLogin.text.toString().trim()
                )

                // Calling the Api to check if the given details of the User are correct or not
                myViewModel.postLoginRequest(postLoginData)
            }
        }

        // Setting the on click Listener on the Sign Up Button
        binding.btnGotoSignUp.setOnClickListener {

            //Changing the Activity from this to the Signup Activity
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

            //Finishing the Current Activity so that it wont be shown if the User hits back
            finish()
        }

        // This code will be executed when we receive a response to our login Request
        myViewModel.userLoginResponse.observe(this) { response ->

            // If the Response is Successful then we set the TOKEN in the Constants and change the Activity to Home Activity
            if (response.isSuccessful) {
                Toast.makeText(this , "Login Successful Redirecting Inside !!" , Toast.LENGTH_SHORT).show()

                // Setting the AccessToken of the User to the Constants
                Constants().setAccessToken(response.body()!!.accessToken)

                // Setting the UserData to the Constants Class
                Constants().setUserData(response.body()!!.user)

                //Changing the Activity from this to the Home Activity
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)

                //Finishing the Current Activity so that it wont be shown if the User hits back
                finish()
            } else
                Toast.makeText(this, "Incorrect Credentials", Toast.LENGTH_SHORT).show()

        }
    }

    // This function setups all the Necessary Instances required in this activity
    private fun setupInstances(){

        //Accessing Login Page View Model
        myViewModel = ViewModelProvider(this , LoginPageViewModelFactory())[LoginPageViewModel::class.java]
    }
}