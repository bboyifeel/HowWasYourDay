package com.lu.uni.igorzfeel.howwasyourday

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogoSplashActivity: AppCompatActivity() {

    companion object {
        val TAG: String = "LogoSplashActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    sleep(750)
                    checkIfLoggedIn()
                } catch (e: Exception) {
                    Log.d(TAG, e.toString())
                }

            }
        }

        background.start()
    }

    private fun checkIfLoggedIn() {
        var user = FirebaseAuth.getInstance().uid
        if (user==null) {
            val intent = Intent(baseContext, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                .or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        else {
            val intent = Intent(baseContext, SettingsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                .or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }
}