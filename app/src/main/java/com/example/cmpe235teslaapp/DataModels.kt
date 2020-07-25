package com.example.cmpe235teslaapp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class Credential(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val created_at: Int
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class Vehicle(
    val id: Long,
    val vin: String,
    val display_name: String
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class VehicleData(
    val response: VehicleDataResponse
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class VehicleDataResponse(
    val charge_state: ChargeState,
    val vehicle_state: VehicleState
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class ChargeState(
    val battery_level: Int,
    val battery_range: Float
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class VehicleState(
    val odometer: Float
)

data class Vehicles(
    val count: Int,
    val response: List<Vehicle>
)