package com.example.visitus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)

        loginBtn.setOnClickListener {
            Toast.makeText(this, "Login Button Clicked", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener {
            Toast.makeText(this, "Sign Up Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}

