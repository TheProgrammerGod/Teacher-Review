package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.databinding.ItemTeacherReviewDetailsRowBinding
import com.example.teacherreview.models.IndividualReviewData
import com.example.teacherreview.models.RatingParameterData

class TeacherReviewDetailsAdapter : ListAdapter<IndividualReviewData , TeacherReviewDetailsAdapter.TeacherReviewDetailsViewHolder>
    (TeacherReviewDetailsComparator()) {

    inner class TeacherReviewDetailsViewHolder(val binding : ItemTeacherReviewDetailsRowBinding) : RecyclerView.ViewHolder(binding.root){

        // This is an array which contains the five ImageViews which is there in each recyclerView row
        val starsOverallRating : List<ImageView> = listOf(
            binding.ivStar1ItemTeacherReviewDetails,
            binding.ivStar2ItemTeacherReviewDetails,
            binding.ivStar3ItemTeacherReviewDetails,
            binding.ivStar4ItemTeacherReviewDetails,
            binding.ivStar5ItemTeacherReviewDetails
        )

        // This is an array which contains the five ImageViews of Teaching stars
        val starsTeachingRating : List <ImageView> = listOf(
            binding.ivStar1ItemReviewDetailsTeachingRating ,
            binding.ivStar2ItemReviewDetailsTeachingRating ,
            binding.ivStar3ItemReviewDetailsTeachingRating ,
            binding.ivStar4ItemReviewDetailsTeachingRating ,
            binding.ivStar5ItemReviewDetailsTeachingRating ,
        )

        // This is an array which contains the five ImageViews of Marks stars
        val starsMarksRating : List<ImageView> = listOf(
            binding.ivStar1ItemReviewDetailsMarkingRating ,
            binding.ivStar2ItemReviewDetailsMarkingRating ,
            binding.ivStar3ItemReviewDetailsMarkingRating ,
            binding.ivStar4ItemReviewDetailsMarkingRating ,
            binding.ivStar5ItemReviewDetailsMarkingRating ,
        )

        // This is an array which contains the five ImageViews of Attendance stars
        val starsAttendanceRating : List<ImageView> = listOf(
            binding.ivStar1ItemReviewDetailsAttendanceRating ,
            binding.ivStar2ItemReviewDetailsAttendanceRating ,
            binding.ivStar3ItemReviewDetailsAttendanceRating ,
            binding.ivStar4ItemReviewDetailsAttendanceRating ,
            binding.ivStar5ItemReviewDetailsAttendanceRating ,
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherReviewDetailsViewHolder {
        val binding = ItemTeacherReviewDetailsRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TeacherReviewDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherReviewDetailsViewHolder, position: Int) {

        val review = getItem(position)

        //TODO :-  Review Giver Name goes here (Need to correct it)
        holder.binding.tvTeacherNameItemTeacherReviewDetails.text = review.faculty.name

        // Review of the Student Goes Here
        holder.binding.tvReviewItemTeacherReviewDetails.text = review.review

        // Stars (ImageView) are set accordingly to the overallRating
        if(review.rating?.overallRating != null)
            calculateStars(review.rating.overallRating , holder.starsOverallRating)

        // Calling the setUI function to set the UI of the teaching rating
        if(review.rating?.teachingRating != null)
            setUI(
                review.rating.teachingRating ,
                holder.starsTeachingRating ,
                holder.binding.tvReviewItemReviewDetailsTeachingReview ,
                holder.binding.layoutItemTeacherReviewDetails ,
                holder.binding.teachingRatingLayout
            )

        // Calling the setUI function to set the UI of the marking rating
        if(review.rating?.markingRating != null)
            setUI(
                review.rating.markingRating ,
                holder.starsMarksRating ,
                holder.binding.tvReviewItemReviewDetailsMarkingReview ,
                holder.binding.layoutItemTeacherReviewDetails ,
                holder.binding.marksRatingLayout
            )

        // Calling the setUI function to set the UI of the attendance rating
        if(review.rating?.attendanceRating != null)
            setUI(
                review.rating.attendanceRating ,
                holder.starsAttendanceRating ,
                holder.binding.tvReviewItemReviewDetailsAttendanceRating ,
                holder.binding.layoutItemTeacherReviewDetails ,
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
    class TeacherReviewDetailsComparator : DiffUtil.ItemCallback<IndividualReviewData> (){
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