package com.example.teacherreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.teacherreview.databinding.FragmentStudentReviewHistoryBinding
import com.example.teacherreview.viewmodels.SharedViewModel

class StudentReviewHistoryFragment : Fragment() {

    private lateinit var binding : FragmentStudentReviewHistoryBinding

    // Initializing the SharedViewModel
    private val sharedViewModel : SharedViewModel by activityViewModels()

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

        //Observable to observe for changes in the ReviewList
        sharedViewModel.myTeacherList.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){
                // TODO :- Call the Recycler View and update the list
            }
            else
                Toast.makeText(requireContext() , "No Data!! Try again Later" , Toast.LENGTH_LONG).show()
        }

        //Initially Taking the Values to update the RecyclerView
        sharedViewModel.getStudentReviewList(1223123) // TODO :- Student Roll in the parameter
    }
}