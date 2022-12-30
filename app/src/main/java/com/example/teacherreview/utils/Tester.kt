package com.example.teacherreview.utils

import com.example.teacherreview.R

//Testing :-------------------------------------------------------------------------------------------------
data class Tester(val name : String ,
                  val sub : String ,
                  val prPic : Int ,
                  val starRating : Double
)
class TestRunner{
    companion object{
        //Testing :-----------------------------------------------------------------------------------------------------
        fun startTesting() : ArrayList<Tester>{
            val items = ArrayList<Tester>()
            for(i in 1..100){
                val item = Tester("Anirban Basak$i" , "Object Oriented Prog." , R.drawable.test_image_icon , 5.0)
                items.add(item)
            }
            return items
        }
    }
}