package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemTeacherReviewDetailsRowBinding
import com.example.teacherreview.models.IndividualReviewData

class TeacherReviewDetailsAdapter : RecyclerView.Adapter<TeacherReviewDetailsAdapter.ViewHolder>() {

    private var reviewList : List <IndividualReviewData> = emptyList()

    inner class ViewHolder( val binding : ItemTeacherReviewDetailsRowBinding) : RecyclerView.ViewHolder(binding.root){
        val stars : List<ImageView> = listOf(
                    binding.ivStar1ItemTeacherReviewDetails,
                    binding.ivStar2ItemTeacherReviewDetails,
                    binding.ivStar3ItemTeacherReviewDetails,
                    binding.ivStar4ItemTeacherReviewDetails,
                    binding.ivStar5ItemTeacherReviewDetails
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeacherReviewDetailsRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvTeacherNameItemTeacherReviewDetails.text = reviewList[position].faculty.name
        holder.binding.tvReviewItemTeacherReviewDetails.text = reviewList[position].review

        // point is the Average Point of each review given by student
        var point = 2.99
        var count = 0
        while(point.toInt() >= 0.9){
            holder.stars[count].setImageResource(R.drawable.full_star_icon)
            count++
            point-=1
        }
        if(point >= 0.5)
            holder.stars[count].setImageResource(R.drawable.half_star_icon)
        // TODO :- Get the avg Rating of each review when we finally get the Api endpoint for that
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    // This Function Updates the data of the RecyclerView
    fun updateData(newList : List<IndividualReviewData>){
        reviewList = newList
        notifyDataSetChanged()
    }
}