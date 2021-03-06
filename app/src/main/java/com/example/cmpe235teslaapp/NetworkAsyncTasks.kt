package com.example.cmpe235teslaapp

import android.os.AsyncTask
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

private var url = "https://owner-api.teslamotors.com/"
private val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .callTimeout(30, TimeUnit.SECONDS)
    .build()
private val mapper = jacksonObjectMapper()
private var client_id: String? = "client id"
private var client_secret: String? = "client secret"

class NetworkAsyncTasks {
    class GetFromApi(val path: String, val token: String?) : AsyncTask<String?, Void?, String>() {
        override fun doInBackground(vararg p0: String?): String {
            val request = Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization", "Bearer $token")
                .url(url + path)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                return response.body!!.string()
            }
        }
    }

    class PostToApi(val body: String, val token: String?, val path: String) : AsyncTask<String?, Void?, String>() {
        override fun doInBackground(vararg p0: String?): String {
            val request = Request.Builder().url(url + path)
                .post(body.toRequestBody(MEDIA_TYPE_MARKDOWN))

            if (token != ""){
                request.addHeader("Content-Type","application/json")
                    .addHeader("Authorization", "Bearer $token")
            }

            client.newCall(request.build()).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                return response.body!!.string()
            }
        }

        companion object {
            val MEDIA_TYPE_MARKDOWN = "application/json; charset=utf-8".toMediaType()
        }
    }
}

fun loginCall(username: String, password: String): String? {
    val body = """
            {
              "grant_type": "password",
              "client_id": "$client_id",
              "client_secret": "$client_secret",
              "email": "$username",
              "password": "$password"
            }
    """.trimIndent()

    return try{
        val resp = NetworkAsyncTasks.PostToApi(body, "", "oauth/token?grant_type=password").execute().get()
        val cred: Credential = mapper.readValue(resp)
        cred.access_token
    } catch (e: IOException) {
        "Login is not successful"
    }
}

fun getVehicles(token: String?): Vehicles {
    val resp = NetworkAsyncTasks.GetFromApi("api/1/vehicles", token).execute().get()
    return mapper.readValue(resp)
}

fun wakeVehicle(token: String?, id: Long) {
    NetworkAsyncTasks.PostToApi("", token,"api/1/vehicles/$id/wake_up").execute().get()
}

fun getVehicleData(token: String?, id: Long): VehicleData {
    val resp =  NetworkAsyncTasks.GetFromApi("api/1/vehicles/$id/vehicle_data", token).execute().get()
    return mapper.readValue(resp)
}

fun unlockVehicle(token: String?, id: Long) {
    NetworkAsyncTasks.PostToApi("", token,"api/1/vehicles/$id/command/door_unlock").execute().get()
}

fun lockVehicle(token: String?, id: Long) {
    NetworkAsyncTasks.PostToApi("", token, "api/1/vehicles/$id/command/door_lock").execute().get()
}

fun flashHeadlights(token: String?, id: Long) {
    NetworkAsyncTasks.PostToApi("", token, "api/1/vehicles/$id/command/flash_lights").execute().get()
}

fun honkHorn(token: String?, id: Long) {
    NetworkAsyncTasks.PostToApi("", token, "api/1/vehicles/$id/command/honk_horn").execute().get()
}