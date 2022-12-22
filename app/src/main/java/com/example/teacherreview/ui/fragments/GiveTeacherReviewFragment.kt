package com.example.teacherreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teacherreview.databinding.FragmentGiveTeacherReviewBinding

class GiveTeacherReviewFragment : Fragment() {

    private lateinit var binding : FragmentGiveTeacherReviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiveTeacherReviewBinding.inflate(inflater , container , false)
        return binding.root
    }
}