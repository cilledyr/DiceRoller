package com.cilledyr.diceroller

import android.content.Intent
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
private var hislist = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            hislist = savedInstanceState.getSerializable("arr") as ArrayList<String>
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(findViewById(R.id.toolbar))
            val thelistView: ListView = findViewById(R.id.listView1)
            thelistView.setAdapter(HistoryArrayAdapter(this,
                    RollDiceApp.sharedPrefsManager.getString(ISharedPrefsManager.Key.history)?.split("|")?: listOf()
            ))




        /*his?.toMutableList()
        val arrayAdapter: ArrayAdapter<*>
        var mListView = findViewById<ListView>(R.id.listView1)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, his!!
        )
        mListView.adapter = arrayAdapter*/
    }



    fun clear(view: View) {
        val thelistView = findViewById<ListView>(R.id.listView1)
        thelistView.adapter = null
        RollDiceApp.sharedPrefsManager.put(ISharedPrefsManager.Key.history,null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("arr", hislist)
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}