package com.example.visitus

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.visitus.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val doneBtn = findViewById<Button>(R.id.doneBtn)

        doneBtn.setOnClickListener {
            Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
        }
    }
}