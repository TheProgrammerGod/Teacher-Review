package com.example.teacherreview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.models.ReviewData

class TeacherListAdapter(private val context: Context) : RecyclerView.Adapter<TeacherListAdapter.TeacherListViewHolder>() {

    private var reviewDataCard : List<ReviewData> = emptyList()

    inner class TeacherListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // TODO :- Bind variables to the UI elements
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_teacher_review_card_row , parent , false)
        return TeacherListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
         return reviewDataCard.size
    }

    // This Function Updates the data of the RecyclerView
    fun updateData(newList : List<ReviewData>){
        reviewDataCard = newList
        notifyDataSetChanged()
    }
}