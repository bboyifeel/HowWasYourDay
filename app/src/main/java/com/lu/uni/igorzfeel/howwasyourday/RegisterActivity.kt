package com.lu.uni.igorzfeel.howwasyourday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener {
            val username = username_edittext_register.text.toString()
            val email = email_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()

            Log.d("RegisterActivity", "Username is: $username")
            Log.d("RegisterActivity", "Email is: $email")
            Log.d("RegisterActivity", "Password is: $password")
        }
        login_text_view_register.setOnClickListener{
            Log.d("RegisterActivity", "Trying to loging")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}