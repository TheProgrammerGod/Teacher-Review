package com.example.teacherreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentTeacherReviewDetailsBinding
import com.example.teacherreview.ui.adapters.TeacherReviewDetailsAdapter
import com.example.teacherreview.utils.TestRunner.Companion.startTesting

class TeacherReviewDetailsFragment : Fragment() {

    // Creating the Adapter for the RecyclerView
    private lateinit var  myAdapter : TeacherReviewDetailsAdapter
    private lateinit var binding: FragmentTeacherReviewDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeacherReviewDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting clickListener on the Floating Action Button which navigates to the giveTeacherReviewFragment
        binding.btnAddReview.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_TeacherReviewDetailsFragment_to_giveTeacherReviewFragment)
        }

        myAdapter = TeacherReviewDetailsAdapter()
        binding.recyclerViewTeacherDetails.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.recyclerViewTeacherDetails.adapter = myAdapter

        // Testing :-------------------------------------------------------------------------------------------------
        myAdapter.updateData(startTesting())
    }
}
/*TODO :-- The viewModel is not yet Implemented and the recyclerView instance
   assigning needs to be done in a different function also needs to setup the observables*/