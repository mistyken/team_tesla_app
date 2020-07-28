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

        val remoteAction: Button = findViewById(R.id.button)
        val historicBattery: Button = findViewById(R.id.button2)
        val odometer: TextView = findViewById(R.id.odometer)
        val vin: TextView = findViewById(R.id.vin_number)
        val carName: TextView = findViewById(R.id.car_name)
        val batteryLevel: TextView = findViewById(R.id.battery_level)

        val token = intent.extras!!.getString("token")
        val vehicles = getVehicles(token)
        val cid = vehicles.response[0].id
        wakeVehicle(token, cid)
        val vehicle_data: VehicleData = getVehicleData(token, cid)

        vin.text = vehicles.response[0].vin
        carName.text = vehicles.response[0].display_name
        odometer.text = vehicle_data.response.vehicle_state.odometer.toString()
        batteryLevel.text = vehicle_data.response.charge_state.battery_level.toString() + "%"

        remoteAction.setOnClickListener {
            val intent = Intent(this, RemoteActionView::class.java).apply {
                putExtra("token", token)
                putExtra("cid", cid)
            }

            startActivity(intent)
        }

        historicBattery.setOnClickListener {
            val intent = Intent(this, HistoricBatteryData::class.java).apply {
                putExtra("cid", cid)
            }

            startActivity(intent)
        }
    }
}