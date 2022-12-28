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

class TeacherListAdapter(private val context: Context) : RecyclerView.Adapter<TeacherListAdapter.TeacherListViewHolder>() {

//    private var reviewDataCard : List<ReviewData> = emptyList()
                //Testing The real Variable is the Above Line --------------------------------------------
    private var reviewDataCard : List<Tester> = emptyList()

    inner class TeacherListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        // Testing :- The real function body is yet to be implemented !!-----------------------------------
        val tvTeacherName : TextView = itemView.findViewById(R.id.tvTeacherName)
        val tvSubject : TextView = itemView.findViewById(R.id.tvSubject)
        val ivProfilePic : ImageView = itemView.findViewById(R.id.ivProfilePic)
        val ivStar1 : ImageView = itemView.findViewById(R.id.ivStar1)
        val ivStar2 : ImageView = itemView.findViewById(R.id.ivStar2)
        val ivStar3 : ImageView = itemView.findViewById(R.id.ivStar3)
        val ivStar4 : ImageView = itemView.findViewById(R.id.ivStar4)
        val ivStar5 : ImageView = itemView.findViewById(R.id.ivStar5)

        //TODO :- Implement the Real function Body as the above is just for testing purposes !! :------------------------
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_teacher_list_row , parent , false)
        return TeacherListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherListViewHolder, position: Int) {

        //Testing :- The real function is yet to be Implemented
        holder.ivProfilePic.setImageResource( reviewDataCard[position].prPic)
        holder.tvTeacherName.text = reviewDataCard[position].name
        holder.tvSubject.text = reviewDataCard[position].sub

        //TODO :- Implement the Real Function Body as the above is just for Testing purose !! :--------------------------
    }

    override fun getItemCount(): Int {
         return reviewDataCard.size
    }

    // This Function Updates the data of the RecyclerView
//    fun updateData(newList : List<ReviewData>){
    //Testing , The real Function prototype is the above Line
    fun updateData(newList : List<Tester>){
        reviewDataCard = newList
        notifyDataSetChanged()
    }
}