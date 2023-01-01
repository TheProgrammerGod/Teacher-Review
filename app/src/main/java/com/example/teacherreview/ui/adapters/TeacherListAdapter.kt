package com.example.teacherreview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherreview.R
import com.example.teacherreview.utils.Tester

class TeacherListAdapter(private val myListener : RecyclerViewOnItemClick) : RecyclerView.Adapter<TeacherListAdapter.TeacherListViewHolder>() {

//    private var reviewDataCard : List<ReviewData> = emptyList()
                //Testing The real Variable is in the Above Line --------------------------------------------
    private var reviewDataCard : List<Tester> = emptyList()

    // This class extends the onClickListener class which implements the function for handling click events
    inner class TeacherListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        // Testing :- The real function body is yet to be implemented !!-----------------------------------
        val tvTeacherName : TextView = itemView.findViewById(R.id.tvTeacherName_Item_Teacher_List)
        val tvSubject : TextView = itemView.findViewById(R.id.tvSubject_Item_Teacher_List)
        val ivProfilePic : ImageView = itemView.findViewById(R.id.ivProfilePic_Item_Teacher_List)
        val ivStar1 : ImageView = itemView.findViewById(R.id.ivStar1_Item_Teacher_List)
        val ivStar2 : ImageView = itemView.findViewById(R.id.ivStar2_Item_Teacher_List)
        val ivStar3 : ImageView = itemView.findViewById(R.id.ivStar3_Item_Teacher_List)
        val ivStar4 : ImageView = itemView.findViewById(R.id.ivStar4_Item_Teacher_List)
        val ivStar5 : ImageView = itemView.findViewById(R.id.ivStar5_Item_Teacher_List)

        //TODO :- Implement the Real function Body as the above is just for testing purposes !! :------------------------

        // This init block defines the onClickListener on the View
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher_list_row , parent , false)
        return TeacherListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherListViewHolder, position: Int) {

        //Testing :- The real function is yet to be Implemented
        holder.ivProfilePic.setImageResource( reviewDataCard[position].prPic)
        holder.tvTeacherName.text = reviewDataCard[position].name
        holder.tvSubject.text = reviewDataCard[position].sub

        //TODO :- Implement the Real Function Body as the above is just for Testing purpose !! :--------------------------
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