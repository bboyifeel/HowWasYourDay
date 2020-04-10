package com.lu.uni.igorzfeel.howwasyourday

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            performLogin()
        }

        register_textview_login.setOnClickListener{
            finish()
        }
    }

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        Log.d("LoginActivity", "Email is: $email")
        Log.d("LoginActivity", "Password is: $password")

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this
                , "Email or password cannot be empty"
                , Toast.LENGTH_SHORT)
                .show()
            return
        }

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("LoginActivity", "uid: ${it.result?.user?.uid}")
                }
            }
            .addOnFailureListener {
                Toast.makeText(this
                    , "Failed to login user: ${it.message}"
                    , Toast.LENGTH_SHORT)
                    .show()
            }
    }

}