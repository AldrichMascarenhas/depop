package com.nerdcutlet.depop.data.remote.okhttp

import com.nerdcutlet.depop.data.remote.network.NetworkChecker
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class NetworkStateInterceptor constructor(
    private val networkChecker: NetworkChecker
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (networkChecker.isNetworkAvailable) {
            proceedRequest(chain)
        } else {
            throw NetworkException()
        }
    }

    private fun proceedRequest(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (e: IOException) {
            throw mapException(e)
        }
    }

    private fun mapException(ioException: IOException): IOException {
        return when (ioException) {
            is ConnectException, is SocketTimeoutException, is UnknownHostException -> NetworkException(ioException)
            else -> ioException
        }
    }
}
