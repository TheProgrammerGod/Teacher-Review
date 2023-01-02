package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemTeacherListRowBinding
import com.example.teacherreview.models.IndividualFacultyData

class TeacherListAdapter(private val myListener : RecyclerViewOnItemClick) : RecyclerView.Adapter<TeacherListAdapter.TeacherListViewHolder>() {

    private var myTeacherList : List<IndividualFacultyData> = emptyList()

    // This class extends the onClickListener class which implements the function for handling click events
    inner class TeacherListViewHolder(val binding: ItemTeacherListRowBinding) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener{

        // Initializing all the required Variables and onClick Events on the init block
        init {
            itemView.setOnClickListener(this)
        }

        // This function is called when a certain item of a recyclerView is CLicked
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                /**
                 * We are calling our custom made RecyclerViewOnItemClick class which have this function
                 * implemented on the fragment and the rest code is handled there rather than in the
                 * adapter class since this part is related to UI and switching Fragment
                 */
                myListener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherListViewHolder {
        val binding = ItemTeacherListRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TeacherListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TeacherListViewHolder, position: Int) {

        // TODO :- Profile Pic
        holder.binding.tvTeacherNameItemTeacherList.text = myTeacherList[position].name
        // TODO :- Subject Name
        val point = myTeacherList[position].avgRating

        if(point == 5.0){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar3ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar4ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar5ItemTeacherList.setImageResource(R.drawable.full_star_icon)
        }
        else if(point >= 4.5){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar3ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar4ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar5ItemTeacherList.setImageResource(R.drawable.half_star_icon)
        }
        else if(point >= 4.0){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar3ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar4ItemTeacherList.setImageResource(R.drawable.full_star_icon)
        }
        else if(point >= 3.5){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar3ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar4ItemTeacherList.setImageResource(R.drawable.half_star_icon)
        }
        else if(point >= 3.0){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar3ItemTeacherList.setImageResource(R.drawable.full_star_icon)
        }
        else if(point >= 2.5){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar3ItemTeacherList.setImageResource(R.drawable.half_star_icon)
        }
        else if(point >= 2.0){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.full_star_icon)
        }
        else if(point >= 1.5){
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
            holder.binding.ivStar2ItemTeacherList.setImageResource(R.drawable.half_star_icon)
        }
        else if(point >= 1.0)
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.full_star_icon)
        else if(point >= 0.5)
            holder.binding.ivStar1ItemTeacherList.setImageResource(R.drawable.half_star_icon)
    }

    override fun getItemCount(): Int {
         return myTeacherList.size
    }

    // This Function Updates the data of the RecyclerView
    fun updateData(newList : List<IndividualFacultyData>){
        myTeacherList = newList
        notifyDataSetChanged()
    }
}