package com.example.coursework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        firebaseAuth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Check if the email contains "@admin"
                if (email.contains("@admin")) {
                    // Redirect to the admin page
                    val adminIntent = Intent(this, AdminMenuActivity::class.java)
                    startActivity(adminIntent)
                    return@setOnClickListener
                }

                // If not an admin, proceed with regular login
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this, "Authentication Passed!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // Login failed, display an error message to the user.
                            Toast.makeText(
                                this, "Authentication failed. Check your email and password.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                // Handle empty email or password fields.
                Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onRegisterClick(view: View) {
        // Handle the registration link click, e.g., navigate to the registration activity
        val registrationIntent = Intent(this, RegistrationActivity::class.java)
        startActivity(registrationIntent)
    }

}