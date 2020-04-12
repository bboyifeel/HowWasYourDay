package com.lu.uni.igorzfeel.howwasyourday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FriendsActivity: AppCompatActivity() {

    companion object {
        val TAG: String = "FriendsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        supportActionBar?.title = "Friends"
    }
}