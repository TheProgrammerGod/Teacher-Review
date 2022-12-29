package com.example.teacherreview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.models.ReviewData
import com.example.teacherreview.ui.fragments.Tester

class StudentReviewHistoryAdapter(private val context : Context) : RecyclerView.Adapter<StudentReviewHistoryAdapter.StudentReviewHistoryViewHolder>() {

    //    private var reviewList : List <ReviewData> = emptyList()
                        //Testing The real Variable is the Above Line --------------------------------------------
    private var reviewList : List <Tester> = emptyList()

    inner class StudentReviewHistoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        // Testing :- The real function body is yet to be implemented !!-----------------------------------
        val ivProfilePic : ImageView = itemView.findViewById(R.id.ivProfilePic_Item_Review_History)
        val tvTeacherName : TextView = itemView.findViewById(R.id.tvTeacherName_Item_Review_History)
        val ivStar1 : ImageView = itemView.findViewById(R.id.ivStar1_Item_Review_History)
        val ivStar2 : ImageView = itemView.findViewById(R.id.ivStar2_Item_Review_History)
        val ivStar3 : ImageView = itemView.findViewById(R.id.ivStar3_Item_Review_History)
        val ivStar4 : ImageView = itemView.findViewById(R.id.ivStar4_Item_Review_History)
        val ivStar5 : ImageView = itemView.findViewById(R.id.ivStar5_Item_Review_History)

        //TODO :- Implement the Real function Body as the above is just for testing purposes !! :------------------------
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentReviewHistoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student_review_history_row , parent , false)
        return StudentReviewHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentReviewHistoryViewHolder, position: Int) {

        //Testing :- The real function is yet to be Implemented
        holder.tvTeacherName.text = reviewList[position].name
        holder.ivProfilePic.setImageResource(reviewList[position].prPic)

        //TODO :- Implement the Real Function Body as the above is just for Testing purpose !! :--------------------------
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    // This Function Updates the data of the RecyclerView
//    fun updateData(newList : List<ReviewData>){
    //Testing , The real Function prototype is the above Line
    fun updateData(newList : List<Tester>){
        reviewList = newList
        notifyDataSetChanged()
    }
}