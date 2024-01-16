package com.damilola.remote.network_factories


import com.damilola.remote.interceptors.HttpsInterceptor
import com.damilola.remote.interceptors.NoInternetInterceptor
import com.damilola.remote.interceptors.UnsuccessfulCallInterceptor
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkFactory @Inject constructor(
    private val moshi: Moshi,
    private val client: OkHttpClient) {

    fun createRetrofit(url: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
            .delegatingCallFactory { client }
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Retrofit.Builder.delegatingCallFactory(
        delegate: dagger.Lazy<OkHttpClient>
    ): Retrofit.Builder = callFactory {
        delegate.get().newCall(it)
    }

    private inline fun Retrofit.Builder.callFactory(
        crossinline body: (Request) -> Call
    ): Retrofit.Builder = callFactory { request -> body(request) }


}