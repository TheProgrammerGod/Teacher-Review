package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemStudentReviewHistoryRowBinding
import com.example.teacherreview.models.IndividualReviewData
import com.example.teacherreview.models.RatingParameterData

class StudentReviewHistoryAdapter : androidx.recyclerview.widget.ListAdapter<IndividualReviewData , StudentReviewHistoryAdapter.StudentReviewHistoryViewHolder>
    (StudentReviewHistoryComparator()){

    // ViewHolder Class for the Student Review Adapter
    inner class StudentReviewHistoryViewHolder(val binding: ItemStudentReviewHistoryRowBinding) : RecyclerView.ViewHolder(binding.root){

        // This is an array which contains the five ImageViews of Overall stars
        val starsOverallRating : List<ImageView> = listOf(
            binding.ivStar1ItemReviewHistory,
            binding.ivStar2ItemReviewHistory,
            binding.ivStar3ItemReviewHistory,
            binding.ivStar4ItemReviewHistory,
            binding.ivStar5ItemReviewHistory
        )

        // This is an array which contains the five ImageViews of Teaching stars
        val starsTeachingRating : List <ImageView> = listOf(
            binding.ivStar1ItemReviewHistoryTeachingRating ,
            binding.ivStar2ItemReviewHistoryTeachingRating ,
            binding.ivStar3ItemReviewHistoryTeachingRating ,
            binding.ivStar4ItemReviewHistoryTeachingRating ,
            binding.ivStar5ItemReviewHistoryTeachingRating ,
            )

        // This is an array which contains the five ImageViews of Marks stars
        val starsMarksRating : List<ImageView> = listOf(
            binding.ivStar1ItemReviewHistoryMarksRating ,
            binding.ivStar2ItemReviewHistoryMarksRating ,
            binding.ivStar3ItemReviewHistoryMarksRating ,
            binding.ivStar4ItemReviewHistoryMarksRating ,
            binding.ivStar5ItemReviewHistoryMarksRating ,
            )

        // This is an array which contains the five ImageViews of Attendance stars
        val starsAttendanceRating : List<ImageView> = listOf(
            binding.ivStar1ItemReviewHistoryAttendanceRating ,
            binding.ivStar2ItemReviewHistoryAttendanceRating ,
            binding.ivStar3ItemReviewHistoryAttendanceRating ,
            binding.ivStar4ItemReviewHistoryAttendanceRating ,
            binding.ivStar5ItemReviewHistoryAttendanceRating ,
            )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentReviewHistoryViewHolder {
        val binding = ItemStudentReviewHistoryRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return StudentReviewHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentReviewHistoryViewHolder, position: Int) {

        // review item of current Position
        val review = getItem(position)

        // Teacher Name goes Here
        holder.binding.tvTeacherNameItemReviewHistory.text = review.faculty.name

        // The main review of the recyclerView goes here
        holder.binding.tvReviewItemReviewHistory.text = review.review

        // Stars (ImageView) are set accordingly to the overallRating if there is some rating
        if(review.rating?.overallRating != null)
            calculateStars(review.rating.overallRating, holder.starsOverallRating)

        // Calling the setUI function to set the UI of the teaching rating
        if(review.rating?.teachingRating != null)
            setUI(
                review.rating.teachingRating ,
                holder.starsTeachingRating ,
                holder.binding.tvReviewItemReviewHistoryTeachingReview ,
                holder.binding.layoutItemReviewHistoryRating ,
                holder.binding.teachingRatingLayout
            )

        // Calling the setUI function to set the UI of the marking rating
        if(review.rating?.markingRating != null)
            setUI(
                review.rating.markingRating ,
                holder.starsMarksRating ,
                holder.binding.tvReviewItemReviewHistoryMarksReview ,
                holder.binding.layoutItemReviewHistoryRating ,
                holder.binding.marksRatingLayout
            )

        // Calling the setUI function to set the UI of the attendance rating
        if(review.rating?.attendanceRating != null)
            setUI(
                review.rating.attendanceRating ,
                holder.starsAttendanceRating ,
                holder.binding.tvReviewItemReviewHistoryAttendanceReview ,
                holder.binding.layoutItemReviewHistoryRating ,
                holder.binding.attendanceRatingLayout
            )
    }

    // Function which sets the UI of the Stars (ImageViews) and the description if any
    private fun setUI(rating : RatingParameterData, stars : List<ImageView>, tvReview : TextView, parentLayout: LinearLayoutCompat, individualRatingLayout : ConstraintLayout){

        // If both description and rating is Nonnull
        if(rating.description != null && rating.ratedPoints != null){
            tvReview.text = rating.description
            calculateStars(rating.ratedPoints , stars)
        }
        // If description is Null and rating is Nonnull
        else if(rating.description == null && rating.ratedPoints != null){
            individualRatingLayout.removeView(tvReview)
            calculateStars(rating.ratedPoints , stars)
        }
        // If description is Nonnull and rating is Null
        else if(rating.description != null)
            tvReview.text = rating.description
        // If both are Null
        else
            parentLayout.removeView(individualRatingLayout)
    }

    // Calculating the Stars that needs to be Visible and then setting the resources of the stars accordingly
    private fun calculateStars(pointsParam : Double , stars : List <ImageView>){
        var points = pointsParam
        var count = 0
        while(points.toInt() >= 0.9){
            stars[count].setImageResource(R.drawable.full_star_icon)
            count++
            points-=1
        }
        if(points >= 0.5)
            stars[count].setImageResource(R.drawable.half_star_icon)
    }

    // DiffUtil class which compares the newList to the oldList
    class StudentReviewHistoryComparator : DiffUtil.ItemCallback<IndividualReviewData>() {
        override fun areItemsTheSame(
            oldItem: IndividualReviewData,
            newItem: IndividualReviewData
        ): Boolean {
            return (oldItem._id == newItem._id)
        }

        override fun areContentsTheSame(
            oldItem: IndividualReviewData,
            newItem: IndividualReviewData
        ): Boolean {
            return (oldItem == newItem)
        }
    }
    //TODO :- Add Paging 3 and implement it
}