package com.example.cmpe235teslaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GeneralView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_view)

        val button: Button = findViewById(R.id.button)
        val odometer: TextView = findViewById(R.id.odometer)
        val vin: TextView = findViewById(R.id.vin_number)
        val car_name: TextView = findViewById(R.id.car_name)
        val battery_level: TextView = findViewById(R.id.battery_level)

        val token = intent.extras?.getString("token")
        if (token != null) {
            val vehicles = getVehicles(token)

            for (vehicle in vehicles.response) {
                vin.text = vehicle.vin
                car_name.text = vehicle.display_name
            }
        }

        button.setOnClickListener {
            val intent = Intent(this, RemoteActionView::class.java)

            startActivity(intent)
        }
    }
}