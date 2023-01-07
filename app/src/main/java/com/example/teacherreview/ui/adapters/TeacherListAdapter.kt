package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemTeacherListRowBinding
import com.example.teacherreview.models.IndividualFacultyData

class TeacherListAdapter(private val myListener : RecyclerViewOnItemClick) : RecyclerView.Adapter<TeacherListAdapter.TeacherListViewHolder>() {

    // This variable is used to store the data of Individual Faculty
    private var myTeacherList : List<IndividualFacultyData> = emptyList()

    // This class extends the onClickListener class which implements the function for handling click events
    inner class TeacherListViewHolder(val binding: ItemTeacherListRowBinding) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener{

        // Initializing all the required Variables and onClick Events on the init block
        init {
            itemView.setOnClickListener(this)
        }


        // stars variable which contains all the star images in an array so we can use a loop to make our code smaller
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
            if(position != RecyclerView.NO_POSITION) {
                /**
                 * We are calling our custom made RecyclerViewOnItemClick class which have this function
                 * implemented on the fragment and the rest code is handled there rather than in the
                 * adapter class since this part is related to UI and switching Fragment
                 */
                myListener.onItemClick(myTeacherList[position]._id)
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
        var point = myTeacherList[position].avgRating
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

    override fun getItemCount(): Int {
         return myTeacherList.size
    }

    // This Function Updates the data of the RecyclerView
    fun updateData(newList : List<IndividualFacultyData>){
        myTeacherList = newList
        notifyDataSetChanged()
    }
}

//TODO :- Make a  List Adapter and add Paging 3 and implement it