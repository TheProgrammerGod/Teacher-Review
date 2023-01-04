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
import com.example.teacherreview.databinding.FragmentTeacherReviewDetailsBinding
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.ui.adapters.TeacherReviewDetailsAdapter
import com.example.teacherreview.viewmodels.SharedViewModel
import retrofit2.Response

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

        sharedViewModel.myDetailedReviewList.observe(viewLifecycleOwner){response ->
            if(response.isSuccessful){
                setupFragmentViews(response)
                myAdapter.updateData(response.body()!!.individualReviewData)
            }
            else{
                Toast.makeText(requireContext() , "Internet Error !! Check Again" , Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun setupInstances(){
        myAdapter = TeacherReviewDetailsAdapter()
        binding.recyclerViewTeacherDetails.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
        binding.recyclerViewTeacherDetails.adapter = myAdapter
    }

    private fun setupFragmentViews(response: Response<ReviewData>){
        binding.tvTitleProfile.text = response.body()!!.individualReviewData[0].faculty.name
        binding.tvRatingTeacherReviewDetails.text = response.body()!!.individualReviewData[0].faculty.avgRating.toString()

        // TODO :- Implement the three different types of Ratings directly from the Teacher Collection database
    }


}
/*TODO :-- The viewModel is not yet Implemented and the recyclerView instance
   assigning needs to be done in a different function also needs to setup the observables*/