package com.example.teacherreview.ui.fragments

import android.os.Bundle
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
import com.example.teacherreview.viewmodels.SharedViewModel

class TeacherListFragment : Fragment() , RecyclerViewOnItemClick {

    // Variable made to update the ,
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

        //Initially Taking the Values once so the Values comes to the RecyclerView
        sharedViewModel.getTeacherList()

        //Observable if the Teacher List changes or user hits any Sort options
        sharedViewModel.teacherList.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){
                myAdapter.submitList(response.body()!!.individualFacultyData)
            }
            else{
                Toast.makeText(requireContext() , "Invalid Parameters. Please Try Again !!" , Toast.LENGTH_LONG).show()
                // TODO :- Make another else block for letting the user know that he don't have net connection
            }
        }

    }

    // Function which setup all the required Instances for the Fragment !
    private fun setupInstances(){
        binding.recyclerViewTeacherList.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)

        // Here this refers to the RecyclerViewOnItemClick Interface class which is implemented below this function
        myAdapter = TeacherListAdapter(this)
        binding.recyclerViewTeacherList.adapter = myAdapter
    }

    // Function which will be invoked when the RecyclerView Item is Clicked
    override fun onItemClick(facultyId : String) {

        // Calling the Function which changes the data for the observable which is then observed by TeacherReviewDetailsFragment
//        sharedViewModel.getDetailedReviews(facultyId)
        sharedViewModel.setFacultyID(facultyId)

        // Changing Fragment to the TeacherReviewDetails Fragment
        val navController = findNavController()
        navController.navigate(R.id.action_teacherListFragment_to_TeacherReviewDetailsFragment)

    }
}