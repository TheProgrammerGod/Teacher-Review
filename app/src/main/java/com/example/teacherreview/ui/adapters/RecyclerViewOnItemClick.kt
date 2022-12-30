package com.example.teacherreview.ui.adapters

/**
 * Custom Interface which can be implemented at different Fragments to handle the code which
 * will execute if an item of RecyclerView is clicked
  */
interface RecyclerViewOnItemClick {
    fun onItemClick(position : Int)
}