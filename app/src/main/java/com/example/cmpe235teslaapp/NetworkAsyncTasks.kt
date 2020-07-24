package com.example.cmpe235teslaapp

import android.os.AsyncTask
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

private var url = "https://owner-api.teslamotors.com/"
private val client = OkHttpClient()
private val mapper = jacksonObjectMapper()

class NetworkAsyncTasks {
    class GetFromApi(val path: String) : AsyncTask<String?, Void?, String>() {
        override fun doInBackground(vararg p0: String?): String {
            val request = Request.Builder()
                .url(url + path)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                return response.body!!.string()
            }
        }
    }

    class PostToApi(val body: String, val path: String) : AsyncTask<String?, Void?, String>() {
        override fun doInBackground(vararg p0: String?): String {
            val request = Request.Builder()
                .url(url + path)
                .post(body.toRequestBody(MEDIA_TYPE_MARKDOWN))
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                return response.body!!.string()
            }
        }

        companion object {
            val MEDIA_TYPE_MARKDOWN = "application/json; charset=utf-8".toMediaType()
        }
    }
}

fun Login(username: String, password: String): String? {
    val body = """
            {
              "grant_type": "password",
              "client_id": "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384",
              "client_secret": "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3",
              "email": "$username",
              "password": "$password"
            }
    """.trimIndent()

    return try{
        val resp = NetworkAsyncTasks.PostToApi(body, "oauth/token?grant_type=password").execute().get()
        val cred: Credential = mapper.readValue(resp)
        cred.access_token
    } catch (e: IOException) {
        "Login is not successful"
    }
}