package com.nerdcutlet.marvel.data.remote.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

internal class NetworkCheckerImpl constructor(
    private val context: Context
) : NetworkChecker {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private fun checkNetworkState(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return connectivityManager.activeNetwork?.let {
                connectivityManager.getNetworkCapabilities(it)
            }?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            @Suppress("deprecation")
            return connectivityManager.activeNetworkInfo?.isConnected == true
        }
    }

    override val isNetworkAvailable: Boolean
        get() = checkNetworkState()
}
