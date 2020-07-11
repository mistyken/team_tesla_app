package com.example.cmpe235teslaapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
//    var url = "https://owner-api.teslamotors.com/"
    var url = "https://ya6s9ee52e.execute-api.us-east-1.amazonaws.com/dev/"
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton: Button = findViewById(R.id.sign_in_button)
        signInButton.setOnClickListener {login()}
    }

    fun login() {
        Toast.makeText(this, "login pressed", Toast.LENGTH_SHORT).show()

        var request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            var txt = response.body!!.string()
            println(txt)
            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
        }
    }
}
