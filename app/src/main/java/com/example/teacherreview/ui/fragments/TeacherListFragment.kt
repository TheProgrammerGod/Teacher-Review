package com.example.teacherreview.ui.fragments

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentTeacherListBinding
import com.example.teacherreview.ui.adapters.RecyclerViewOnItemClick
import com.example.teacherreview.ui.adapters.TeacherListAdapter
import com.example.teacherreview.utils.TestRunner.Companion.startTesting
import com.example.teacherreview.viewmodels.SharedViewModel

/**
 * Implementing the RecyclerViewOnItemClick interface here so that we can implement all the code
 * in our fragment Class since its related to calling different Fragment and related to UI stuffs
 * which are handled at out Fragment rather than Adapter class
  */

class TeacherListFragment : Fragment() , RecyclerViewOnItemClick {

    private lateinit var binding :FragmentTeacherListBinding
    // Creating the SharedViewModel Instance
    private val sharedViewModel : SharedViewModel by activityViewModels()
    // Creating the Adapter for the RecyclerView
    private lateinit var  myAdapter : TeacherListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeacherListBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up the RecyclerView Instances and all the required Instances !!
        setupInstances()

        // Testing :--------------------------------------------------------------------------------------------
        myAdapter.updateData(startTesting())


        //Observable if the Teacher List changes or user hits any Sort options
        sharedViewModel.myTeacherList.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){
                d("Teacher Review Fragment" , response.body().toString())
                //TODO :- Call the Adapter class function (User - Defined) for the updates of the new ReviewList
            }
            else
                Toast.makeText(requireContext() , "Invalid Parameters. Please Try Again !!" , Toast.LENGTH_LONG).show()
        }

        //Initially Taking the Values once so the Values comes to the RecyclerView
        sharedViewModel.getTeacherReviewList()
    }

    // Function which setup all the required Instances for the Fragment !
    private fun setupInstances(){
        binding.recyclerViewTeacherList.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)

        // Here this refers to the RecyclerViewOnItemClick Interface class which is implemented below this function
        myAdapter = TeacherListAdapter(this)
        binding.recyclerViewTeacherList.adapter = myAdapter
    }

    // Function which will be invoked when the RecyclerView Item is Clicked
    override fun onItemClick(position: Int) {

        // Changing Fragment to the TeacherReviewDetails Fragment
        val navController = findNavController()
        navController.navigate(R.id.action_teacherListFragment_to_TeacherReviewDetailsFragment)

        // This toast is for testing Purposes :---------------------------------------------------------------------
        Toast.makeText(requireContext() , "Teacher Name : $position" , Toast.LENGTH_SHORT).show()
    }
}