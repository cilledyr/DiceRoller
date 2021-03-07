package com.cilledyr.diceroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import java.lang.NumberFormatException


class HistoryArrayAdapter(context: Context, private val values: List<String?>) : ArrayAdapter<String?>(context, R.layout.list_history, values) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.list_history, parent, false)
        val textView = rowView.findViewById<View>(R.id.textListTime) as TextView
        val imageView1 = rowView.findViewById<View>(R.id.imgListDice1) as ImageView
        val imageView2 = rowView.findViewById<View>(R.id.imgListDice2) as ImageView
        val imageView3 = rowView.findViewById<View>(R.id.imgListDice3) as ImageView
        val imageView4 = rowView.findViewById<View>(R.id.imgListDice4) as ImageView
        val imageView5 = rowView.findViewById<View>(R.id.imgListDice5) as ImageView
        val imageView6 = rowView.findViewById<View>(R.id.imgListDice6) as ImageView
        //textView.text = values[position]
        val diceIds = arrayOf(
                R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
        )
        // Change icon based on name
        val s = values[position]
        if(s != null) {
            val stringArrayText = s.split("=")[1].trim()
            textView.text = stringArrayText
            val numbersArray: List<String> = s.split("=")[0].replace("=", "").trim().split(" ")

            val thefinalArray = mutableListOf<Int>()
            numbersArray.forEach { item ->
                item.trim();
                val theInt = findInteger(item);
                thefinalArray.add(theInt)
                }

            if (thefinalArray.size != 6) {
                println("Something weird has occurred")
            } else {
                if (thefinalArray[5] == -1) {
                    imageView6.isInvisible = true
                } else {
                    imageView6.setImageResource(diceIds[thefinalArray[5]])
                }
                if (thefinalArray[4] == -1) {
                    imageView5.isInvisible = true
                } else {
                    imageView5.setImageResource(diceIds[thefinalArray[4]])
                }
                if (thefinalArray[3] == -1) {
                    imageView4.isInvisible = true
                } else {
                    imageView4.setImageResource(diceIds[thefinalArray[3]])
                }
                if (thefinalArray[2] == -1) {
                    imageView3.isInvisible = true
                } else {
                    imageView3.setImageResource(diceIds[thefinalArray[2]])
                }
                if (thefinalArray[1] == -1) {
                    imageView2.isInvisible = true
                } else {
                    imageView2.setImageResource(diceIds[thefinalArray[1]])
                }
                imageView1.setImageResource(diceIds[thefinalArray[0]])
            }
        }
        else {
            println("Something unexpected happened.")
        }

        /*if (s == "WindowsMobile") {
            imageView.setImageResource(R.drawable.windowsmobile_logo)
        } else if (s == "iOS") {
            imageView.setImageResource(R.drawable.ios_logo)
        } else if (s == "Blackberry") {
            imageView.setImageResource(R.drawable.blackberry_logo)
        } else {
            imageView.setImageResource(R.drawable.android_logo)
        }*/
        return rowView
    }

    private fun findInteger(theword: String): Int {
        try {
            val theInt = theword.toInt()
            return theInt
        }catch (e: NumberFormatException) {
            return -1;
        }
    }
}