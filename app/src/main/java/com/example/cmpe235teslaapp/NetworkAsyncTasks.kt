package com.example.cmpe235teslaapp

import android.os.AsyncTask
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

var url = "https://owner-api.teslamotors.com/"

class NetworkAsyncTasks {
    class GetFromApi : AsyncTask<String?, Void?, String>() {
        private val client = OkHttpClient()

        override fun doInBackground(vararg p0: String?): String {
            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                return response.body!!.string()
            }
        }
    }

    class PostToApi(username: String, password: String) : AsyncTask<String?, Void?, String>() {
        private val body = """
            {
              "grant_type": "password",
              "client_id": "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384",
              "client_secret": "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3",
              "email": "$username",
              "password": "$password"
            }
        """.trimIndent()

        private val client = OkHttpClient()

        override fun doInBackground(vararg p0: String?): String {
            println(body)
            val request = Request.Builder()
                .url(url + "oauth/token?grant_type=password")
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