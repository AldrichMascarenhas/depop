package com.nerdcutlet.marvel.data

import android.content.Context
import androidx.room.Room
import com.nerdcutlet.marvel.data.local.database.MARVEL_HERO_DATABASE
import com.nerdcutlet.marvel.data.local.database.MarvelHeroDatabase
import com.nerdcutlet.marvel.data.remote.network.NetworkChecker
import com.nerdcutlet.marvel.data.remote.network.NetworkCheckerImpl
import com.nerdcutlet.marvel.data.remote.okhttp.MarvelInterceptor
import com.nerdcutlet.marvel.data.remote.okhttp.NetworkStateInterceptor
import com.nerdcutlet.marvel.data.remote.repository.MarvelRepositoryImpl
import com.nerdcutlet.marvel.data.remote.retrofit.service.MarvelService
import com.nerdcutlet.marvel.domain.repository.MarvelRepository
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

    bind() from singleton { MarvelInterceptor() }

    bind() from singleton { NetworkStateInterceptor(instance()) }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .addInterceptor(instance<HttpLoggingInterceptor>())
            .addInterceptor(instance<MarvelInterceptor>())
            .addInterceptor(instance<NetworkStateInterceptor>())
            .build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl("https://gateway.marvel.com:443/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(instance())
            .build()
    }

    // Remote
    bind<MarvelService>() with singleton {
        instance<Retrofit>().create(MarvelService::class.java)
    }

    // Dao
    bind() from singleton {
        instance<MarvelHeroDatabase>().marvelDao()
    }

    bind<MarvelRepository>() with singleton {
        MarvelRepositoryImpl(
            instance(),
            instance()
        )
    }

    // Local Room
    bind<MarvelHeroDatabase>() with singleton {
        Room.databaseBuilder(
            instance<Context>(),
            MarvelHeroDatabase::class.java,
            MARVEL_HERO_DATABASE
        )
            .build()
    }
}
