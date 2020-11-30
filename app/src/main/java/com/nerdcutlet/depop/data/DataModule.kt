package com.nerdcutlet.depop.data

import com.nerdcutlet.depop.data.remote.network.NetworkChecker
import com.nerdcutlet.depop.data.remote.network.NetworkCheckerImpl
import com.nerdcutlet.depop.data.remote.okhttp.NetworkStateInterceptor
import com.nerdcutlet.depop.data.remote.repository.DepopRepositoryImpl
import com.nerdcutlet.depop.data.remote.retrofit.service.DepopService
import com.nerdcutlet.depop.domain.repository.DepopRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = DI.Module(name = "dataModule") {

    // Network
    bind<HttpLoggingInterceptor>() with singleton {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<NetworkChecker>() with singleton {
        NetworkCheckerImpl(instance())
    }

    bind() from singleton { NetworkStateInterceptor(instance()) }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .addInterceptor(instance<HttpLoggingInterceptor>())
            .addInterceptor(instance<NetworkStateInterceptor>())
            .build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl("https://api.garage.me/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(instance())
            .build()
    }

    // Remote
    bind<DepopService>() with singleton {
        instance<Retrofit>().create(DepopService::class.java)
    }

    bind<DepopRepository>() with singleton {
        DepopRepositoryImpl(
            instance()
        )
    }
}
