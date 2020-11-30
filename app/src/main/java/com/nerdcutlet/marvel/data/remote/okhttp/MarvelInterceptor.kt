package com.nerdcutlet.marvel.data.remote.okhttp

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class MarvelInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()

        val currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()).toString()
        val privateApiKey = "7fb67e14fc89bf7380550678ccaf3bb69d66a8a7"
        val publicApiKey = "8bafb9744056288ba6e5fb2131614045"

        val hash = generateHash(currentTime, privateApiKey, publicApiKey)

        val newurl = url
            .addQueryParameter("apikey", publicApiKey)
            .addQueryParameter("hash", hash)
            .addQueryParameter("ts", currentTime)
            .build()

        val newRequest = request.newBuilder().url(url = newurl).build()

        return chain.proceed(newRequest)
    }

    private fun String.toMD5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digested = md.digest(toByteArray())
        return digested.joinToString("") { String.format("%02x", it) }
    }

    private fun generateHash(
        currentTime: String,
        privateApiKey: String,
        publicApiKey: String
    ): String {
        return (currentTime.toString() + privateApiKey + publicApiKey).toMD5()
    }
}
