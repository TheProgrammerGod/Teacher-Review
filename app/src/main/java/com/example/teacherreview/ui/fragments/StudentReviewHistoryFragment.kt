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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.databinding.FragmentStudentReviewHistoryBinding
import com.example.teacherreview.ui.adapters.StudentReviewHistoryAdapter
import com.example.teacherreview.utils.Constants
import com.example.teacherreview.viewmodels.SharedViewModel

class StudentReviewHistoryFragment : Fragment() {

    private lateinit var binding : FragmentStudentReviewHistoryBinding
    // Initializing the SharedViewModel
    private val sharedViewModel : SharedViewModel by activityViewModels()
    // Initializing the adapter for RecyclerView
    private lateinit var myAdapter : StudentReviewHistoryAdapter

    // This is a Linear Layout Manager variable which contains the layout manager of the recyclerView
    private lateinit var layoutManager: LinearLayoutManager

    // isScrolling Variable is made to check if the scrolling is finished or still on
    private var isScrolling = false

    // Variable to store the current Number of Items present on the Recycler View Screen
    private var currentItems = 0

    // Variable to store the scrolled Out Items from the RecyclerView
    private var scrolledOutItems = 0

    // Variable to store the total size of the list present to the recyclerView
    private var totalItems = 0

    // Variable which acts as a stopper to stop the fetching of data from the server once all the data is fetched
    private var stopFetching = false

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

        // Fetching the Data from the Server for the First time
        fetchData()

        // OnScrollListener of the recyclerView which asks for more data when the user reaches the end of the recyclerView
        binding.recyclerViewReviewHistory.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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

                // storing the total items of the Layout Manager
                totalItems = layoutManager.itemCount

                // Storing the scrolled Out Items of the recyclerView
                if(layoutManager.findFirstCompletelyVisibleItemPosition() != -1)
                    scrolledOutItems = layoutManager.findFirstCompletelyVisibleItemPosition()

                // checking if we should stop asking for more data as the server reached its limit
                stopFetching = layoutManager.itemCount != sharedViewModel.studentReviewHistoryLimit

                // If the User Reaches the end of the List we asks for more data
                if(isScrolling && (currentItems+scrolledOutItems == totalItems) && !stopFetching){
                   fetchData()
                }
            }
        })

        //Observable to observe for changes in the ReviewList
        sharedViewModel.studentReviewHistoryList.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){

                // Passing the Data to the Adapter class
                myAdapter.submitList(response.body()!!.individualReviewData)
            }
            else
                Toast.makeText(requireContext() , "No Data!! Try again Later" , Toast.LENGTH_LONG).show()

            // Disabling the Progress Bar as we got out data
            binding.progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    // Function which setup all the required Instances for the Fragment !
    private fun setupInstance() {
        myAdapter = StudentReviewHistoryAdapter()
        layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.recyclerViewReviewHistory.layoutManager = layoutManager
        binding.recyclerViewReviewHistory.adapter = myAdapter
    }

    // Function which asks for data from the server and shows a progress bar till we get out data
    private fun fetchData(){

        // Setting the Progress Bar visibility on
        binding.progressBar.visibility = ProgressBar.VISIBLE
        isScrolling = false

        // Increasing the Limit of the fetching data to get more data from the Server
        sharedViewModel.studentReviewHistoryLimit += 15

        // Calling for the Review History with the user's Id
        sharedViewModel.getStudentReviewList(Constants.MYUSERDATA._id)
    }
}