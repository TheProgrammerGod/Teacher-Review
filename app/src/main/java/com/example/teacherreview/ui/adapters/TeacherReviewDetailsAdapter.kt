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
import com.example.teacherreview.databinding.HeaderTeacherReviewDetailsRowBinding
import com.example.teacherreview.databinding.ItemTeacherReviewDetailsRowBinding
import com.example.teacherreview.models.IndividualReviewData
import com.example.teacherreview.models.RatingParameterData

class TeacherReviewDetailsAdapter(private val myListener : TeacherDetailedReviewHeaderImplementation) : ListAdapter<IndividualReviewData , RecyclerView.ViewHolder>
    (TeacherReviewDetailsComparator()) {

    // This Variable contains the size of the List + HEADER_SIZE
    private var listSize = 0

    // These variables are made for the Header Type and the Item Type
    companion object{
        private const val HEADER_TYPE = 0
        private const val LIST_TYPE = 1
        private const val HEADER_SIZE = 1
    }

    // Teacher Review Items ViewHolder Class
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

    // Teacher Review Header View Holder Class
    inner class TeacherReviewDetailsHeaderViewHolder(val binding: HeaderTeacherReviewDetailsRowBinding) : RecyclerView.ViewHolder(binding.root)

    // Returning the ViewType as Header type or the actual list item type
    override fun getItemViewType(position: Int): Int {
        if(position == 0)
            return HEADER_TYPE
        return LIST_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // Checking if the View Type is List_Type if not we execute the code below
        if(viewType == LIST_TYPE) {
            val binding = ItemTeacherReviewDetailsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TeacherReviewDetailsViewHolder(binding)
        }
        val binding = HeaderTeacherReviewDetailsRowBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TeacherReviewDetailsHeaderViewHolder(binding)
    }

    // This function returns the  listSize along with the Header
    override fun getItemCount(): Int {
        return listSize
    }

    // This function is made to get the List size of the ReviewData and then assign the recyclerView listSize
    fun submitListSize(size : Int){
        listSize = size + HEADER_SIZE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position : Int) {

        // If the Passed ViewHolder is of Teacher Review Item type then this block runs
        if(holder is TeacherReviewDetailsViewHolder) {

            /**
             * We use the current position - 1 here because at position 0 header
             * file is there and then comes the real data
             *
             * We take the current Review and its Rating data from the list and
             * then bind the data to the UI accordingly
              */
            val review = getItem(position - 1)
            val ratings = listOf(
                review.rating?.teachingRating?.ratedPoints ,
                review.rating?.markingRating?.ratedPoints ,
                review.rating?.attendanceRating?.ratedPoints
                )

            //Review Giver Name Goes Here
            holder.binding.tvTeacherNameItemTeacherReviewDetails.text = review.createdBy.name

            // Review of the Student Goes Here
            holder.binding.tvReviewItemTeacherReviewDetails.text = review.review

            // Stars (ImageView) are set accordingly to the Review Overall Rating given by User
            var avgReviewRating = 0.0
            var totalRatings = 0
            for(rating in ratings){
                if(rating != null) {
                    avgReviewRating += rating
                    totalRatings++
                }
            }
            avgReviewRating /= totalRatings
            if (avgReviewRating != 0.0)
                calculateStars(avgReviewRating , holder.starsOverallRating)

            // Calling the setUI function to set the UI of the teaching rating
            if (review.rating?.teachingRating != null)
                setUI(
                    review.rating.teachingRating,
                    holder.starsTeachingRating,
                    holder.binding.tvReviewItemReviewDetailsTeachingReview,
                    holder.binding.layoutItemTeacherReviewDetails,
                    holder.binding.teachingRatingLayout
                )

            // Calling the setUI function to set the UI of the marking rating
            if (review.rating?.markingRating != null)
                setUI(
                    review.rating.markingRating,
                    holder.starsMarksRating,
                    holder.binding.tvReviewItemReviewDetailsMarkingReview,
                    holder.binding.layoutItemTeacherReviewDetails,
                    holder.binding.marksRatingLayout
                )

            // Calling the setUI function to set the UI of the attendance rating
            if (review.rating?.attendanceRating != null)
                setUI(
                    review.rating.attendanceRating,
                    holder.starsAttendanceRating,
                    holder.binding.tvReviewItemReviewDetailsAttendanceRating,
                    holder.binding.layoutItemTeacherReviewDetails,
                    holder.binding.attendanceRatingLayout
                )
        }

        // If the Passed ViewHolder is of Teacher Review Header Type then this block runs
        if( holder is TeacherReviewDetailsHeaderViewHolder){

            // Calling the Custom interface which is implemented in the Fragment
            myListener.setTeacherDetailedReviewHeaderUI(holder.binding)
        }
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
}