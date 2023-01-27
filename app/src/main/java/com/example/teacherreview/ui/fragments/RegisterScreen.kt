package com.example.teacherreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentRegisterScreenBinding
import com.example.teacherreview.models.PostSignupData
import com.example.teacherreview.viewmodels.RegisterScreenViewModel
import com.example.teacherreview.viewmodels.RegisterScreenViewModelFactory

class RegisterScreen : Fragment() {

    // Binding Variable
    private lateinit var binding : FragmentRegisterScreenBinding
    // NavController variable
    private lateinit var navController: NavController
    // ViewModel
    private lateinit var myViewModel : RegisterScreenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterScreenBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up all the Instances of the Fragment which will be needed throughout the Fragment
        setupInstances()

        // Setting on click listener to the Signup button which posts new user Post request
        binding.cardRegisterButton.setOnClickListener {

            // Variables with the Values entered by the User
            val name = binding.etFullNameRegister.text.toString()
            val email = binding.etEmailRegister.text.toString()
            val password = binding.etTextPasswordRegister.text.toString()
            val confirmPassword = binding.etTextConfirmPasswordRegister.text.toString()

            // Checking if the edit Text Fields are empty
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty())
                Toast.makeText(requireContext(), "Empty Details please Fill the Form again!!", Toast.LENGTH_SHORT).show()
            else if(password != confirmPassword)
                Toast.makeText(requireContext() , "Passwords Don't Match" , Toast.LENGTH_SHORT).show()
            else {

                // Creating the object which will be the body of the post Request
                val postSignupData = PostSignupData(
                    name = name ,
                    email = email,
                    password = password
                )

                // Calling the Api to make a new user
                myViewModel.postSignupRequest(postSignupData)
            }
        }

        // This code will be executed when we receive a response to our register Request
        myViewModel.userSignupData.observe(viewLifecycleOwner){response ->

            // If the response is successful then we direct the user to the login Activity
            if(response.isSuccessful){
                Toast.makeText(requireContext() , "User Created" , Toast.LENGTH_SHORT).show()

                //Changing the Activity from this to the Login Activity
                navController.navigate(R.id.action_registerScreen_to_loginScreen)
            }
            else
                Toast.makeText(requireContext() , "Failed to Create" , Toast.LENGTH_SHORT).show()
        }
    }

    // This function setups all the Instances which are needed for this Fragment
    private fun setupInstances(){

        // Setting up the Nav Controller
        navController = findNavController()

        //Accessing Login Page View Model
        myViewModel = ViewModelProvider(this , RegisterScreenViewModelFactory())[RegisterScreenViewModel::class.java]
    }
}