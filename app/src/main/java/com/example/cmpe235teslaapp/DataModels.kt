package com.example.cmpe235teslaapp

data class Credential(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val created_at: Int
)