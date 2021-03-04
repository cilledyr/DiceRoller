package com.cilledyr.diceroller

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {

    private val diceIds = arrayOf(
        R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
        R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
    )
private var his = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(findViewById(R.id.toolbar))
         his= intent.getStringArrayListExtra("list") as ArrayList<String>
        his?.toMutableList()
        val arrayAdapter: ArrayAdapter<*>
        var mListView = findViewById<ListView>(R.id.listView1)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, his!!
        )
        mListView.adapter = arrayAdapter
    }


    override fun  onBackPressed() {
      his.clear()
        super.onBackPressed()
    }

}