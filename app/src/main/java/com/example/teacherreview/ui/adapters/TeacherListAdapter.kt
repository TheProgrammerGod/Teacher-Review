package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemTeacherListRowBinding
import com.example.teacherreview.models.IndividualFacultyData

class TeacherListAdapter(private val myListener : RecyclerViewOnItemClick) : ListAdapter<IndividualFacultyData , TeacherListAdapter.TeacherListViewHolder>(Comparator()) {

    // This class extends the onClickListener class which implements the function for handling click events
    inner class TeacherListViewHolder(val binding: ItemTeacherListRowBinding) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener{

        // Initializing all the required Variables and onClick Events on the init block
        init {
            itemView.setOnClickListener(this)
        }

        // stars variable which contains all the overall rating star (ImageView)
        val stars : List<ImageView> = listOf(
            binding.ivStar1ItemTeacherList,
            binding.ivStar2ItemTeacherList,
            binding.ivStar3ItemTeacherList,
            binding.ivStar4ItemTeacherList,
            binding.ivStar5ItemTeacherList
        )

        // This function is called when a certain item of a recyclerView is CLicked
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
                myListener.onItemClick(getItem(position)._id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherListViewHolder {
        val binding = ItemTeacherListRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TeacherListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TeacherListViewHolder, position: Int) {

        val faculty = getItem(position)

        // TODO :- Profile Pic
        holder.binding.tvTeacherNameItemTeacherList.text = faculty.name
        // TODO :- Subject Name
        var point =faculty.avgRating
        var count = 0
        while (point.toInt() >= 0.9){
            holder.stars[count].setImageResource(R.drawable.full_star_icon)
            point-= 1
            count++
        }
        if(point >= 0.5){
            holder.stars[count].setImageResource(R.drawable.half_star_icon)
        }
    }

    // DiffUtil class which compares the newList to the oldList
    class Comparator : DiffUtil.ItemCallback<IndividualFacultyData>(){
        override fun areItemsTheSame(
            oldItem: IndividualFacultyData,
            newItem: IndividualFacultyData
        ): Boolean {
            return (oldItem._id == newItem._id)
        }

        override fun areContentsTheSame(
            oldItem: IndividualFacultyData,
            newItem: IndividualFacultyData
        ): Boolean {
            return (newItem == oldItem)
        }
    }
}