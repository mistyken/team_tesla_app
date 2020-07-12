package com.example.cmpe235teslaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cmpe235teslaapp.NetworkAsyncTasks.PostToApi
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton: Button = findViewById(R.id.sign_in_button)
        signInButton.setOnClickListener {login()}
    }

    private fun login() {
        try {
            val username: EditText = findViewById(R.id.editTextTextUsername)
            val password: EditText = findViewById(R.id.editTextTextPassword)
            val txt = PostToApi(username.text.toString(), password.text.toString()).execute().get()
            Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Login failed. Please try again", Toast.LENGTH_SHORT).show()
        }
    }
}
