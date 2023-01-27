package com.example.teacherreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentGetAllYouWantBinding

class GetAllYouWant : Fragment() {

    // Binding Variable
    private lateinit var binding : FragmentGetAllYouWantBinding

    // NavController variable
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGetAllYouWantBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up all the Instances of the Fragment which will be needed throughout the Fragment
        setupInstances()

        binding.cardForwardButton.setOnClickListener {
            navController.navigate(R.id.action_getAllYouWant_to_loginOrRegister)
        }
    }

    // This function setups all the Instances which are needed for this Fragment
    private fun setupInstances(){
        navController = findNavController()
    }
}