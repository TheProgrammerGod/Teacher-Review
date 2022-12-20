package com.example.teacherreview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.teacherreview.databinding.FragmentTeacherListBinding

class TeacherListFragment : Fragment() {

    private lateinit var binding :FragmentTeacherListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeacherListBinding.inflate(inflater , container , false)
        return binding.root
    }
}