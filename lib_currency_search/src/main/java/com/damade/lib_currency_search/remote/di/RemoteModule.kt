package com.damade.lib_currency_search.remote.di

import com.damade.lib_currency_search.data.contract.remote.CurrencyRemote
import com.damade.lib_currency_search.remote.ApiService
import com.damade.lib_currency_search.remote.impl.CurrencyRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface RemoteModule {

    @get:Binds
    val  CurrencyRemoteImpl.bindCurrencyRemote: CurrencyRemote

    companion object {
        @[Provides Singleton]
        fun apiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)
    }

}