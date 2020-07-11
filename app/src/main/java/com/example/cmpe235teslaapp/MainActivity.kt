package com.example.cmpe235teslaapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var txtString: TextView? = null
    var url = "https://reqres.in/api/users/2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        Toast.makeText(this, "login pressed", Toast.LENGTH_SHORT).show()

    }
}
