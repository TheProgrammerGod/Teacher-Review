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
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentGiveTeacherReviewBinding
import com.example.teacherreview.models.*
import com.example.teacherreview.viewmodels.SharedViewModel

class GiveTeacherReviewFragment : Fragment() {

    private lateinit var binding : FragmentGiveTeacherReviewBinding

    // Variables which contains the Ratings of the different Parameters
    private var ivTeachingRating : Int? = null
    private var ivMarkingRating : Int? = null
    private var ivAttendanceRating : Int? = null

    // Lists of all the different stars ImageView in the layout
    private lateinit var starsTeaching : List<ImageView>
    private lateinit var starsMarking : List<ImageView>
    private lateinit var starsAttendance : List<ImageView>

    // Initializing the SharedViewModel
    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiveTeacherReviewBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // List of Teaching stars in the Layout
        starsTeaching = listOf(
            binding.ivTeachingStar1 ,
            binding.ivTeachingStar2 ,
            binding.ivTeachingStar3 ,
            binding.ivTeachingStar4 ,
            binding.ivTeachingStar5 ,
            )

        // List of Marking stars in the Layout
        starsMarking = listOf(
            binding.ivMarkingStar1 ,
            binding.ivMarkingStar2 ,
            binding.ivMarkingStar3 ,
            binding.ivMarkingStar4 ,
            binding.ivMarkingStar5 ,
            )

        // List of Attendance stars in the Layout
        starsAttendance = listOf(
            binding.ivAttendanceStar1 ,
            binding.ivAttendanceStar2 ,
            binding.ivAttendanceStar3 ,
            binding.ivAttendanceStar4 ,
            binding.ivAttendanceStar5 ,
            )

        // setOnClickListener set on the Teaching Stars
        binding.ivTeachingStar1.setOnClickListener {
            calculateStars(1 , starsTeaching)
        }
        binding.ivTeachingStar2.setOnClickListener {
            calculateStars(2 , starsTeaching)
        }
        binding.ivTeachingStar3.setOnClickListener {
            calculateStars(3 , starsTeaching)
        }
        binding.ivTeachingStar4.setOnClickListener {
            calculateStars(4 , starsTeaching)
        }
        binding.ivTeachingStar5.setOnClickListener {
            calculateStars(5 , starsTeaching)
        }


        // setOnClickListener set on the Marking Stars
        binding.ivMarkingStar1.setOnClickListener {
            calculateStars(1 , starsMarking)
        }
        binding.ivMarkingStar2.setOnClickListener {
            calculateStars(2 , starsMarking)
        }
        binding.ivMarkingStar3.setOnClickListener {
            calculateStars(3 , starsMarking)
        }
        binding.ivMarkingStar4.setOnClickListener {
            calculateStars(4 , starsMarking)
        }
        binding.ivMarkingStar5.setOnClickListener {
            calculateStars(5 , starsMarking)
        }


        // setOnClickListener set on the Attendance Stars
        binding.ivAttendanceStar1.setOnClickListener {
            calculateStars(1 , starsAttendance)
        }
        binding.ivAttendanceStar2.setOnClickListener {
            calculateStars(2 , starsAttendance)
        }
        binding.ivAttendanceStar3.setOnClickListener {
            calculateStars(3 , starsAttendance)
        }
        binding.ivAttendanceStar4.setOnClickListener {
            calculateStars(4 , starsAttendance)
        }
        binding.ivAttendanceStar5.setOnClickListener {
            calculateStars(5 , starsAttendance)
        }


        // setOnClickListener on the submit button which will add the Review to the Teacher
        binding.btnSubmit.setOnClickListener {

            // Review Texts input by Users are stored in the variables
            val teachingReview = if(binding.etTeachingReview.text.toString() == "") null else binding.etTeachingReview.text.toString()
            val markingReview = if(binding.etMarkingReview.text.toString() == "") null else binding.etMarkingReview.text.toString()
            val attendanceReview = if(binding.etAttendanceReview.text.toString() == "") null else binding.etAttendanceReview.text.toString()

            // Creating the RatingData model object to be passed to the retrofit for posting
            val ratingData = RatingData(
                overallRating = null ,
                teachingRating = RatingParameterData(
                    ratedPoints = ivTeachingRating?.toDouble() ,
                    description = teachingReview
                ),
                markingRating = RatingParameterData(
                    ratedPoints = ivMarkingRating?.toDouble() ,
                    description = markingReview
                ),
                attendanceRating = RatingParameterData(
                    ratedPoints = ivAttendanceRating?.toDouble() ,
                    description = attendanceReview
                ),
            )

            // The Actual post data that will be sent to the Database
            val post = ReviewPostData(
                review = binding.etOverallReview.text.toString() ,
                rating = ratingData,
                faculty = sharedViewModel.facultyId!!,
                // TODO :- Real Subject needs to be implemented here
                subject = "63b206d844b81bcd4940d1e6"
            )

            // Posting the User Review
            sharedViewModel.postTeacherReview(post)
        }

        // observing the postReview object to understand if our post is successful or not
        sharedViewModel.postReview.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful)
                Toast.makeText(requireContext() , "Review posted Successfully !!" , Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(requireContext() , "Something Went Wrong please try again After checking the Internet" , Toast.LENGTH_SHORT).show()

            val navController = findNavController()
            navController.navigate(R.id.action_giveTeacherReviewFragment_to_showTeacherReviewFragment)
        }
    }

    // Calculating the Stars that needs to be Visible and then setting the resources of the stars accordingly
    private fun calculateStars(pointsParam : Int , stars : List <ImageView>){

        // This function call refreshes the UI i.e it updates the stars UI to its default value
        refreshUI(stars)

        var points = pointsParam
        var count = 0
        while(points >= 1){
            stars[count].setImageResource(R.drawable.full_star_icon)
            count++
            points-=1
        }

        when (stars) {
            starsTeaching -> ivTeachingRating = pointsParam
            starsMarking -> ivMarkingRating = pointsParam
            else -> ivAttendanceRating = pointsParam
        }
    }

    // This function refreshes the UI of the stars to its default Value
    private fun refreshUI(stars : List<ImageView>){
        for(i in 0..4){
            stars[i].setImageResource(R.drawable.zero_star_icon)
        }
    }

    //TODO:- Need to fix the issue that after submitting the Review we cant open the giveReviewFragment again
}