package com.cilledyr.diceroller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var alist = arrayListOf<String>()
    public var s = ""
    private val mGenerator = Random()
    private val diceIds = arrayOf(
        R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
        R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
    )

    val mHistory = mutableListOf<List<Int>>()
    private val nrOfDice = arrayOf(1, 2, 3, 4, 5, 6)
    private var amountOfDice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
           alist = savedInstanceState.getSerializable("array") as ArrayList<String>
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinnerAmountOfDice.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            nrOfDice
        )
        spinnerAmountOfDice.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                amountOfDice = nrOfDice[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                amountOfDice = 1
            }
        }
    }

    fun onClickRoll(v: View) {
        val result = rollDice()

        if(mHistory.size == 10)
        {
            mHistory.removeAt(0)
        }

        mHistory.add(result)
        updateHistory()
    }

    private fun updateHistory() {
        val dateFormat = SimpleDateFormat("HH:mm:ss")
        var time: String? = dateFormat.format(Date())
        mHistory.forEach{ list ->    list.forEach{ dice -> if(dice != -1){
                                                            val nr = dice +1;
                                                            s += "$nr "};
                                };

        }
            /*val e1 = p.first; val e2 = p.second
                           s += "$e1 - $e2\n"}*/
        s += "=$time"
        alist.add(s)
         s = ""
        mHistory.clear()

    }



    private fun rollDice() :List<Int> {
        var d1 = -1
        var d2 = -1
        var d3 = -1
        var d4 = -1
        var d5 = -1
        var d6 = -1
        if(amountOfDice >= 5)
        {
            diceBoard3.isInvisible = false
            if(amountOfDice == 6) {
                imgDice6.isInvisible = false
                d5 = mGenerator.nextInt(6)
                d6 = mGenerator.nextInt(6)
                imgDice5.setImageResource(diceIds[d5])
                imgDice6.setImageResource(diceIds[d6])
            }
            else {
                d5 = mGenerator.nextInt(6)
                imgDice6.isInvisible = true
                imgDice5.setImageResource(diceIds[d5])
            }
        }
        else
        {
            diceBoard3.isInvisible = true
        }
        if(amountOfDice >= 3)
        {
            diceBoard2.isInvisible = false
            if(amountOfDice >= 4) {
                imgDice4.isInvisible = false
                d3 = mGenerator.nextInt(6)
                d4 = mGenerator.nextInt(6)
                imgDice3.setImageResource(diceIds[d3])
                imgDice4.setImageResource(diceIds[d4])
            }
            else {
                d3 = mGenerator.nextInt(6)
                imgDice4.isInvisible = true
                imgDice3.setImageResource(diceIds[d3])
            }
        }
        else
        {
            diceBoard2.isInvisible = true
        }
        if(amountOfDice >= 2) {
            imgDice2.isInvisible = false
            d1 = mGenerator.nextInt(6)
            d2 = mGenerator.nextInt(6)
            imgDice2.setImageResource(diceIds[d2])
            imgDice1.setImageResource(diceIds[d1])
        }
        else {
            d1 = mGenerator.nextInt(6)
            imgDice2.isInvisible = true
            imgDice1.setImageResource(diceIds[d1])
        }
        return listOf(d1, d2, d3, d4, d5, d6)
    }

    fun goToHistory(view: View) {
        val intent = Intent(this, History::class.java)
        intent.putStringArrayListExtra("list", alist)
        this.startActivity(intent)
        s=""
    }

    fun clear(view: View) {
        alist.clear()
        s = ""
        mHistory.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("array", alist)
    }


}