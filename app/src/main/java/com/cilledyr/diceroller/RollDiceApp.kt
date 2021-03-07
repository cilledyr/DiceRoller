package com.cilledyr.diceroller

import android.app.Application

class RollDiceApp : Application() {

    companion object{
        lateinit var sharedPrefsManager: ISharedPrefsManager
    }


    override fun onCreate() {
        super.onCreate()
        sharedPrefsManager = SharedPrefsManager(this)
    }
}