package com.example.teacherreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacherreview.databinding.FragmentStudentReviewHistoryBinding
import com.example.teacherreview.ui.adapters.StudentReviewHistoryAdapter
import com.example.teacherreview.viewmodels.SharedViewModel

class StudentReviewHistoryFragment : Fragment() {

    private lateinit var binding : FragmentStudentReviewHistoryBinding
    // Initializing the SharedViewModel
    private val sharedViewModel : SharedViewModel by activityViewModels()
    // Initializing the adapter for RecyclerView
    private lateinit var myAdapter : StudentReviewHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentReviewHistoryBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up the RecyclerView Instances and all the required Instances !!
        setupInstance()

        // TODO :- Dummy Id Binding is done here
        sharedViewModel.getStudentReviewList("63b1f2e644b81bcd4940d18d")

        //Observable to observe for changes in the ReviewList
        sharedViewModel.studentReviewHistoryList.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){

                // Calling a function which calculates the average and sets it (Dummy Setup)
                val newData = sharedViewModel.setTeacherAverageRatings(response.body()!!)
                myAdapter.submitList(newData.individualReviewData)
            }
            else
                Toast.makeText(requireContext() , "No Data!! Try again Later" , Toast.LENGTH_LONG).show()
        }
    }

    // Function which setup all the required Instances for the Fragment !
    private fun setupInstance() {
        myAdapter = StudentReviewHistoryAdapter()
        binding.recyclerViewReviewHistory.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.recyclerViewReviewHistory.adapter = myAdapter
    }
}