package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemStudentReviewHistoryRowBinding
import com.example.teacherreview.models.IndividualReviewData

class StudentReviewHistoryAdapter : RecyclerView.Adapter<StudentReviewHistoryAdapter.StudentReviewHistoryViewHolder>() {

    // This variable stores the data of Individual Review data given
    private var reviewList : List <IndividualReviewData> = emptyList()

    inner class StudentReviewHistoryViewHolder(val binding: ItemStudentReviewHistoryRowBinding) : RecyclerView.ViewHolder(binding.root){

        // This is an array which contains the five ImageViews which is there in each recyclerView row
        val stars : List<ImageView> = listOf(
            binding.ivStar1ItemReviewHistory,
            binding.ivStar2ItemReviewHistory,
            binding.ivStar3ItemReviewHistory,
            binding.ivStar4ItemReviewHistory,
            binding.ivStar5ItemReviewHistory
        )
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentReviewHistoryViewHolder {
        val binding = ItemStudentReviewHistoryRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return StudentReviewHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentReviewHistoryViewHolder, position: Int) {

        // Teacher Name goes Here
        //TODO :- The Student Name should come here instead of the Teacher Name
        holder.binding.tvTeacherNameItemReviewHistory.text = reviewList[position].faculty.name

        // The main review of the recyclerView is goes here
        holder.binding.tvReviewItemReviewHistory.text = reviewList[position].review


        // Stars (ImageView) are set accordingly to the overallRating
        if(reviewList[position].rating.overallRating != null){
            var overallRating = reviewList[position].rating.overallRating
            var count = 0
            while(overallRating?.toInt()!! >= 0.9){
                holder.stars[count].setImageResource(R.drawable.full_star_icon)
                count++
                overallRating-=1
            }
            if(overallRating >= 0.5)
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