package com.example.teacherreview.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.teacherreview.databinding.FragmentLoginScreenBinding
import com.example.teacherreview.models.PostLoginData
import com.example.teacherreview.ui.HomePageActivity
import com.example.teacherreview.utils.Constants
import com.example.teacherreview.viewmodels.LoginScreenViewModel
import com.example.teacherreview.viewmodels.LoginScreenViewModelFactory

class LoginScreen : Fragment() {

    // Binding Variable
    private lateinit var binding : FragmentLoginScreenBinding
    // NavController variable
    private lateinit var navController: NavController
    // ViewModel
    private lateinit var myViewModel : LoginScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLoginScreenBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up all the Instances of the Fragment which will be needed throughout the Fragment
        setupInstances()

        // Setting the on click Listener on the Card Login Button
        binding.cardLoginButton.setOnClickListener {

            // Email and Password entered by the User
            val email = binding.etEmailLogin.text.toString()
            val password = binding.etTextPasswordLogin.text.toString()

            // Checking if the edit Text Fields are empty
            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(requireContext(), "Empty Details please Fill the Form again!!", Toast.LENGTH_SHORT).show()
            else {

                // Creating the object which will be the body of the post Request
                val postLoginData = PostLoginData(
                    email = email,
                    password = password
                )

                // Calling the Api to check if the given details of the User are correct or not
                myViewModel.postLoginRequest(postLoginData)
            }
        }

        // This code will be executed when we receive a response to our login Request
        myViewModel.userLoginResponse.observe(viewLifecycleOwner) { response ->

            // If the Response is Successful then we set the TOKEN in the Constants and change the Activity to Home Activity
            if (response.isSuccessful) {
                Toast.makeText(requireContext() , "Login Successful Redirecting Inside !!" , Toast.LENGTH_SHORT).show()

                // Setting the AccessToken of the User to the Constants
                Constants().setAccessToken(response.body()!!.accessToken)

                // Setting the UserData to the Constants Class
                Constants().setUserData(response.body()!!.user)

                //Changing the Activity from this to the Home Activity
                val intent = Intent(requireContext(), HomePageActivity::class.java)
                startActivity(intent)

                //Finishing the Current Activity so that it wont be shown if the User hits back
                requireActivity().finish()
            } else
                Toast.makeText(requireContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show()
        }
    }

    // This function setups all the Instances which are needed for this Fragment
    private fun setupInstances(){

        // Setting up the NavController
        navController = findNavController()

        //Accessing Login Page View Model
        myViewModel = ViewModelProvider(this , LoginScreenViewModelFactory())[LoginScreenViewModel::class.java]
    }
}