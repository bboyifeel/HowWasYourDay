package com.lu.uni.igorzfeel.howwasyourday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener {
            performRegister()
        }

        login_text_view_register.setOnClickListener{
            Log.d("RegisterActivity", "Trying to loging")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister() {
        val username = username_edittext_register.text.toString()
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this
                , "Email or password cannot be empty"
                , Toast.LENGTH_SHORT)
                .show()
            return
        }
        Log.d("RegisterActivity", "Username is: $username")
        Log.d("RegisterActivity", "Email is: $email")
        Log.d("RegisterActivity", "Password is: $password")

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // succeeded
                Log.d("RegisterActivity", "uid: ${it.result?.user?.uid}")

                val intent = Intent(this, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    .or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this
                    , "Failed to create user: ${it.message}"
                    , Toast.LENGTH_SHORT)
                    .show()
            }
    }

}
