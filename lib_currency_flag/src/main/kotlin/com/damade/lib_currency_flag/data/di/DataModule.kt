package com.damade.lib_currency_flag.data.di


import com.damade.lib_currency_flag.data.repository.CurrencyConversionRepository
import com.damade.lib_currency_flag.data.repository.CurrencyConversionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
internal interface DataModule {

    @get:Binds
    val CurrencyConversionRepositoryImpl.currencyConversionRepository: CurrencyConversionRepository

}