package com.damilola.remote.di

import com.apollographql.apollo3.ApolloClient
import com.damilola.config.AppsConfig
import com.damilola.core.model.ResponseMessage
import com.damilola.remote.interceptors.HttpsInterceptor
import com.damilola.remote.interceptors.NoInternetInterceptor
import com.damilola.remote.interceptors.UnsuccessfulCallInterceptor
import com.damilola.remote.network_factories.ApolloNetworkFactory
import com.damilola.remote.network_factories.NetworkFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    companion object {
        @[Provides Singleton]
        fun provideRetrofit(networkFactory: NetworkFactory, appsConfig: AppsConfig): Retrofit =
            networkFactory.createRetrofit(
                url = appsConfig.BASE_URL
            )

        @[Provides Singleton]
        fun provideApolloClient(networkFactory: ApolloNetworkFactory, appsConfig: AppsConfig): ApolloClient =
            networkFactory.createApollo(
                url = appsConfig.APOLLO_BASE_URL
            )

        @[Provides Singleton]
        fun provideResponseJsonAdapter(moshi: Moshi): JsonAdapter<ResponseMessage> {
            val typeT = Types.newParameterizedType(ResponseMessage::class.java, String::class.java)
            return moshi.adapter(typeT)
        }

        @[Provides Singleton]
        fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpsInterceptor)
                .addInterceptor(NoInternetInterceptor)
                .addInterceptor(UnsuccessfulCallInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()
        }

        @[Provides Singleton]
        fun makeLoggingInterceptor(appsConfig: AppsConfig): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (appsConfig.isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
            return logging
        }
    }

    val provideMoshi: Moshi
        @[Provides Singleton] get() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()
}
