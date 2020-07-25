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

data class Vehicles(
    val count: Int,
    val response: List<Vehicle>
)