package com.example.teacherreview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.models.ReviewData

class StudentReviewHistoryAdapter(private val context : Context) : RecyclerView.Adapter<StudentReviewHistoryAdapter.StudentReviewHistoryViewHolder>() {
    private var reviewList : List <ReviewData> = emptyList()

    inner class StudentReviewHistoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // TODO :- Assign Variables to the UI elements
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentReviewHistoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student_review_row , parent , false)
        return StudentReviewHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentReviewHistoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    // This Function Updates the data of the RecyclerView
    fun updateData(newList : List<ReviewData>){
        reviewList = newList
        notifyDataSetChanged()
    }
}