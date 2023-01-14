package com.example.teacherreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentTeacherReviewDetailsBinding
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.ui.adapters.TeacherReviewDetailsAdapter
import com.example.teacherreview.viewmodels.SharedViewModel

class TeacherReviewDetailsFragment : Fragment() {

    // Creating the Adapter for the RecyclerView
    private lateinit var  myAdapter : TeacherReviewDetailsAdapter
    private lateinit var binding: FragmentTeacherReviewDetailsBinding
    private val sharedViewModel : SharedViewModel by activityViewModels()

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

        setupInstances()

        // Setting clickListener on the Floating Action Button which navigates to the giveTeacherReviewFragment

        // Sharing Amount of the Bank is necessary
        binding.btnAddReview.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_TeacherReviewDetailsFragment_to_giveTeacherReviewFragment)
        }

        sharedViewModel.detailedReviewList.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){

                // Calling Dummy Function to set the Average data for testing purpose
                val newData = sharedViewModel.setTeacherAverageRatings(response.body()!!)

                // Passing the list to the function to set the UI for the current layer
                setupFragmentViews(newData)
                myAdapter.submitList(newData.individualReviewData)
            }
            else{
                Toast.makeText(requireContext() , "Internet Error !! Check Again" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function which setups the required Instances for the Fragment
    private fun setupInstances(){
        myAdapter = TeacherReviewDetailsAdapter()
        binding.recyclerViewTeacherDetails.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.recyclerViewTeacherDetails.adapter = myAdapter
    }

    // This function setups the UI for the current Fragment
    private fun setupFragmentViews(response: ReviewData){

        // Faculty Name Goes here
        binding.tvTitleProfile.text = response.individualReviewData?.get(0)?.faculty?.name

        // Average Rating Goes here
        binding.tvRatingTeacherReviewDetails.text = response.individualReviewData?.get(0)?.faculty?.avgRating.toString()

        // This is an array which contains the five ImageViews of teaching stars
        val teachingStars = listOf<ImageView>(
            binding.ivStar1TeachingTeacherReviewDetails ,
            binding.ivStar2TeachingTeacherReviewDetails ,
            binding.ivStar3TeachingTeacherReviewDetails ,
            binding.ivStar4TeachingTeacherReviewDetails ,
            binding.ivStar5TeachingTeacherReviewDetails ,
            )

        // This is an array which contains the five ImageViews of marking stars
        val markingStars = listOf<ImageView>(
            binding.ivStar1MarksTeacherReviewDetails ,
            binding.ivStar2MarksTeacherReviewDetails ,
            binding.ivStar3MarksTeacherReviewDetails ,
            binding.ivStar4MarksTeacherReviewDetails ,
            binding.ivStar5MarksTeacherReviewDetails ,
            )

        // This is an array which contains the five ImageViews of attendance stars
        val attendanceStars = listOf<ImageView>(
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
}