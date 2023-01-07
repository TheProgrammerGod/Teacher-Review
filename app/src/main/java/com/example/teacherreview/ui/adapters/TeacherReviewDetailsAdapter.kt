package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemTeacherReviewDetailsRowBinding
import com.example.teacherreview.models.IndividualReviewData

class TeacherReviewDetailsAdapter : RecyclerView.Adapter<TeacherReviewDetailsAdapter.TeacherReviewDetailsViewHolder>() {

    private var reviewList : List <IndividualReviewData> = emptyList()

    inner class TeacherReviewDetailsViewHolder(val binding : ItemTeacherReviewDetailsRowBinding) : RecyclerView.ViewHolder(binding.root){

        // This is an array which contains the five ImageViews which is there in each recyclerView row
        val stars : List<ImageView> = listOf(
                    binding.ivStar1ItemTeacherReviewDetails,
                    binding.ivStar2ItemTeacherReviewDetails,
                    binding.ivStar3ItemTeacherReviewDetails,
                    binding.ivStar4ItemTeacherReviewDetails,
                    binding.ivStar5ItemTeacherReviewDetails
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherReviewDetailsViewHolder {
        val binding = ItemTeacherReviewDetailsRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TeacherReviewDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherReviewDetailsViewHolder, position: Int) {

        // Teacher Name goes here
        holder.binding.tvTeacherNameItemTeacherReviewDetails.text = reviewList[position].faculty.name

        // Teacher Review Goes Here
        holder.binding.tvReviewItemTeacherReviewDetails.text = reviewList[position].review

        // Stars (ImageView) are set accordingly to the overallRating
        if(reviewList[position].rating.overallRating != null){
            var point = reviewList[position].rating.overallRating
            var count = 0
            while(point?.toInt()!! >= 0.9){
                holder.stars[count].setImageResource(R.drawable.full_star_icon)
                count++
                point-=1
            }
            if(point >= 0.5)
                holder.stars[count].setImageResource(R.drawable.half_star_icon)
        }
        /*TODO :- Implement the Rest of the Parameters like Teaching rating, marking rating
            attendance rating , and their descriptions
         */
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

//TODO :- Make a  List Adapter and add Paging 3 and implement it