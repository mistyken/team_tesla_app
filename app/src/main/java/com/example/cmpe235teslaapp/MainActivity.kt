package com.example.cmpe235teslaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton: Button = findViewById(R.id.sign_in_button)
        signInButton.setOnClickListener {
            val token = login()
            if (token != "Login is not successful") {
                val intent = Intent(this, GeneralView::class.java).apply {
                    putExtra("token", token)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, token, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun login(): String? {
        val username: EditText = findViewById(R.id.editTextUsername)
        val password: EditText = findViewById(R.id.editTextPassword)
        return loginCall(username.text.toString(), password.text.toString())
    }
}
