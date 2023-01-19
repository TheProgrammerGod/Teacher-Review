package com.example.teacherreview.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    // This is a Linear Layout Manager variable which contains the layout manager of the recyclerView
    private lateinit var layoutManager: LinearLayoutManager

    // This Variable keeps track of number of views on the screen of the recyclerView
    private var currentItems = 0

    // This variable keeps track of scrolled Out Views from the RecyclerView
    private var scrolledOutItems = 0

    // This variable keeps track of the total number of the items in the list of the recyclerView
    private var totalItems = 0

    // isScrolling Variable is made to check if the scrolling is finished or still on
    private var isScrolling = false

    // Variable which acts as a stopper to stop the fetching of data from the server once all the data is fetched
    private var stopFetching = false

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

        // Fetching the data from the Server for the First Time
        fetchData()

        // RecyclerView Scroll Listener which asks the ViewModel to fetch the data from the Server when the User is at the end of the RecyclerView
        binding.recyclerViewTeacherList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // Checking if the User stops scrolling
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    isScrolling = true
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Storing the number of items on the Screen
                currentItems = layoutManager.childCount

                // Storing the total items of the recyclerView
                totalItems = layoutManager.itemCount

                // Storing the scrolled Out Items of the recyclerView
                if(layoutManager.findFirstCompletelyVisibleItemPosition() != -1)
                    scrolledOutItems = layoutManager.findFirstCompletelyVisibleItemPosition()


                // checking if we should stop asking for more data as the server reached its limit
                stopFetching = layoutManager.itemCount != sharedViewModel.studentReviewHistoryLimit

                // If the User Reaches the end of the List we asks for more data
                if((isScrolling && (scrolledOutItems + currentItems == totalItems) && !stopFetching) || totalItems == currentItems){
                    fetchData()
                }
            }
        })

        //Observable if the Teacher List changes or user hits any Sort options
        sharedViewModel.teacherList.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){
                myAdapter.submitList(response.body()!!.individualFacultyData)
            }
            else{
                Toast.makeText(requireContext() , "Invalid Parameters. Please Try Again !!" , Toast.LENGTH_LONG).show()
                // TODO :- Make another else block for letting the user know that he don't have net connection
            }
            binding.progressBar2.visibility = ProgressBar.INVISIBLE
        }
    }

    // Function which setup all the required Instances for the Fragment !
    private fun setupInstances(){

        layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.recyclerViewTeacherList.layoutManager = layoutManager
        // Here this refers to the RecyclerViewOnItemClick Interface class which is implemented below this function
        myAdapter = TeacherListAdapter(this)
        binding.recyclerViewTeacherList.adapter = myAdapter
    }

    // Function which will be invoked when the RecyclerView Item is Clicked
    override fun onItemClick(facultyId : String) {

        // Calling the Function which sets the facultyID
        sharedViewModel.setFacultyID(facultyId)

        // Changing Fragment to the TeacherReviewDetails Fragment
        val navController = findNavController()
        navController.navigate(R.id.action_teacherListFragment_to_TeacherReviewDetailsFragment)

    }

    // This function fetches the data from the Server and shows to The User
    private fun fetchData(){
        binding.progressBar2.visibility = ProgressBar.VISIBLE
        isScrolling = false
        sharedViewModel.teacherListLimit += 10
        sharedViewModel.getTeacherList()
    }
}