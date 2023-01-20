package com.example.teacherreview.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentTeacherReviewDetailsBinding
import com.example.teacherreview.databinding.HeaderTeacherReviewDetailsRowBinding
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.ui.adapters.TeacherDetailedReviewHeaderImplementation
import com.example.teacherreview.ui.adapters.TeacherReviewDetailsAdapter
import com.example.teacherreview.viewmodels.SharedViewModel

class TeacherReviewDetailsFragment : Fragment() , TeacherDetailedReviewHeaderImplementation{

    // Creating the Adapter for the RecyclerView
    private lateinit var  myAdapter : TeacherReviewDetailsAdapter
    private lateinit var binding: FragmentTeacherReviewDetailsBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()

    // This Variable is used to store the reviewList temporarily after a
    private lateinit var response : ReviewData

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
        binding = FragmentTeacherReviewDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This function  sets up the RecyclerView
        setupInstances()

        // This function calls for fetching data initially whenever we fetches data
        fetchData()

        // Setting clickListener on the Floating Action Button which navigates to the giveTeacherReviewFragment
        binding.btnAddReview.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_TeacherReviewDetailsFragment_to_giveTeacherReviewFragment)
        }

        // OnScrollListener of the recyclerView which asks for more data when the user reaches the end of the recyclerView
        binding.recyclerViewTeacherDetails.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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
                stopFetching = (layoutManager.itemCount != (sharedViewModel.teacherDetailReviewLimit + 1))

                // If the User Reaches the end of the List we asks for more data
                if((isScrolling && (currentItems+scrolledOutItems == totalItems) && !stopFetching))
                    fetchData()
            }
        })

        // This observes the detailedReviewList
        sharedViewModel.detailedReviewList.observe(viewLifecycleOwner){
            if(it.isSuccessful){

                // Calling Dummy Function to set the Average data for testing purpose
                val newData = sharedViewModel.setTeacherAverageRatings(it.body()!!)

                // setting temporary response so we can set the Layout Later when needed
                this.response = newData

                // Submitting Values to the RecyclerView
                myAdapter.submitListSize(response.individualReviewData!!.size)
                myAdapter.submitList(response.individualReviewData)
            }
            else
                Toast.makeText(requireContext() , "Internet Error !! Check Again" , Toast.LENGTH_SHORT).show()

            // Setting ProgressBar3 Visibility to Invisible since the data fetching part is done
            binding.progressBar3.visibility = ProgressBar.INVISIBLE
        }
    }

    // Function which setups the required Instances for the Fragment
    private fun setupInstances(){
        myAdapter = TeacherReviewDetailsAdapter(this)
        layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.recyclerViewTeacherDetails.layoutManager = layoutManager
        binding.recyclerViewTeacherDetails.adapter = myAdapter
    }

    // Calculating the Stars that needs to be Visible and then setting the resources of the stars accordingly
    private fun calculateStars(pointsParam : Double , stars : List <ImageView>){
        var points = pointsParam
        var count = 0
        while(points.toInt() >= 0.9){
            stars[count].setImageResource(R.drawable.full_star_icon)
            count++
            points-=1
        }
        if(points >= 0.5)
            stars[count].setImageResource(R.drawable.half_star_icon)
    }

    // Setting the UI of the Header of the RecyclerView (It is an implementation of the TeacherDetailedReviewHeaderImplementation)
    override fun setTeacherDetailedReviewHeaderUI(binding: HeaderTeacherReviewDetailsRowBinding) {
        // Faculty Name Goes here
        binding.tvTitleProfile.text = response.individualReviewData?.get(0)?.faculty?.name

        // Average Rating Goes here
        binding.tvRatingTeacherReviewDetails.text = response.individualReviewData?.get(0)?.faculty?.avgRating.toString()

        // This is an array which contains the five ImageViews of teaching stars
        val teachingStars = listOf(
            binding.ivStar1TeachingTeacherReviewDetails ,
            binding.ivStar2TeachingTeacherReviewDetails ,
            binding.ivStar3TeachingTeacherReviewDetails ,
            binding.ivStar4TeachingTeacherReviewDetails ,
            binding.ivStar5TeachingTeacherReviewDetails ,
            )

        // This is an array which contains the five ImageViews of marking stars
        val markingStars = listOf(
            binding.ivStar1MarksTeacherReviewDetails ,
            binding.ivStar2MarksTeacherReviewDetails ,
            binding.ivStar3MarksTeacherReviewDetails ,
            binding.ivStar4MarksTeacherReviewDetails ,
            binding.ivStar5MarksTeacherReviewDetails ,
            )

        // This is an array which contains the five ImageViews of attendance stars
        val attendanceStars = listOf(
            binding.ivStar1AttendanceTeacherReviewDetails,
            binding.ivStar2AttendanceTeacherReviewDetails,
            binding.ivStar3AttendanceTeacherReviewDetails,
            binding.ivStar4AttendanceTeacherReviewDetails,
            binding.ivStar5AttendanceTeacherReviewDetails,
        )

        // Checking if there exists an avg teaching rating and calling function to set the UI
        if(response.avgTeachingRating != null) {
            calculateStars(response.avgTeachingRating , teachingStars)
        }

        // Checking if there exists an avg marking rating and calling function to set the UI
        if(response.avgMarkingRating != null)
            calculateStars(response.avgMarkingRating , markingStars)

        // Checking if there exists an avg attendance rating and calling function to set the UI
        if(response.avgAttendanceRating != null)
            calculateStars(response.avgAttendanceRating , attendanceStars)
    }

    // Function which asks for data from the server and shows a progress bar till we get out data
    private fun fetchData(){

        // Setting the Progress Bar visibility on
        binding.progressBar3.visibility = ProgressBar.VISIBLE
        isScrolling = false

        // Increasing the Limit of the fetching data to get more data from the Server
        sharedViewModel.teacherDetailReviewLimit += 20
        sharedViewModel.getDetailedReviews()
    }
}