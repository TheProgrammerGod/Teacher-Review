package com.example.teacherreview.ui.fragments

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacherreview.R
import com.example.teacherreview.databinding.FragmentTeacherListBinding
import com.example.teacherreview.ui.adapters.TeacherListAdapter
import com.example.teacherreview.viewmodels.SharedViewModel

class TeacherListFragment : Fragment() {

    private lateinit var binding :FragmentTeacherListBinding
    // Creating the SharedViewModel Instance
    private val sharedViewModel : SharedViewModel by activityViewModels()
    // Creating the Adapter for the RecyclerView
    private lateinit var  myAdapter : TeacherListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeacherListBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting up the RecyclerView Instances and all the required Instances !!
        setupInstances()

        // Testing :--------------------------------------------------------------------------------------------
        startTesting()


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
        myAdapter = TeacherListAdapter(requireContext())
        binding.recyclerViewTeacherList.adapter = myAdapter
    }

    //Testing :-----------------------------------------------------------------------------------------------------
    private fun startTesting(){
        val items = ArrayList<Tester>()
        for(i in 1..100){
            val item = Tester("Anirban Basak$i" , "Object Oriented Prog." , R.drawable.test_image_icon , 5.0)
            items.add(item)
        }
        myAdapter.updateData(items)
    }
}
//Testing :-------------------------------------------------------------------------------------------------
data class Tester(val name : String ,
                  val sub : String ,
                  val prPic : Int ,
                  val starRating : Double
                  )