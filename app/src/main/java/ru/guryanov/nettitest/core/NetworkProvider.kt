package ru.guryanov.nettitest.core

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class NetworkProvider {


    fun providerRetrofit(url: String): NettiApi {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(createHttpClient())
            .build().create(NettiApi::class.java)

    }

    private fun createHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .followRedirects(true)
        return client.build()
    }


    companion object {
        fun createNetworkProvider(url: String): NettiApi {
            return NetworkProvider().providerRetrofit(url)
        }
    }
}