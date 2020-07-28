package com.example.cmpe235teslaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast


class RemoteActionView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_action)

//        val actionBar = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
        val token = intent.extras!!.getString("token")
        val cid = intent.extras!!.getLong("cid")

        val unlock_vehicle: Switch = findViewById(R.id.unlock_vehicle)
        val lock_vehicle: Switch = findViewById(R.id.lock_vehicle)
        val flash_headlight: Switch = findViewById(R.id.flash_headlights)
        val honk_horn: Switch = findViewById(R.id.honk_horn)

        unlock_vehicle.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                unlockVehicle(token, cid)
            }
            Toast.makeText(this, "Vehicle Unlocked",
                Toast.LENGTH_SHORT).show()
        }

        lock_vehicle.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                lockVehicle(token, cid)
            }
            Toast.makeText(this, "Vehicle Locked",
                Toast.LENGTH_SHORT).show()
        }

        flash_headlight.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                flashHeadlights(token, cid)
            }
            Toast.makeText(this, "Flashing Headlights",
                Toast.LENGTH_SHORT).show()
        }

        honk_horn.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                honkHorn(token,cid)
            }
            Toast.makeText(this, "Honking Horn",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}