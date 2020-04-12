package com.lu.uni.igorzfeel.howwasyourday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lu.uni.igorzfeel.howwasyourday.models.UserAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        val TAG: String = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener {
            performRegister()
        }

        login_text_view_register.setOnClickListener{
            Log.d(TAG, "Trying to loging")
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
        Log.d(TAG, "Username is: $username")
        Log.d(TAG, "Email is: $email")
        Log.d(TAG, "Password is: $password")

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // succeeded
                Log.d(TAG, "uid: ${it.result?.user?.uid}")

                sendUserToFirebaseDatabase()

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

    private fun sendUserToFirebaseDatabase() {
        val username = username_edittext_register.text.toString()
        val email    = email_edittext_register.text.toString()
        val user = UserAuth(username, email)

        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseFirestore.getInstance().collection("users")
        ref.document(uid).set(user)
            .addOnSuccessListener {
                Log.d(TAG, "User has been saved to Firestore")
            }
            .addOnFailureListener {
                Log.w(TAG, "Error creating a user in Firestore ", it)
            }

    }


}
