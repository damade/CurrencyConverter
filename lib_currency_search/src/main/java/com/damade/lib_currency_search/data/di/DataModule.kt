package com.damade.lib_currency_search.data.di

import com.damade.lib_currency_search.data.repository.CurrencyConversionRepositoryImpl
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {

    @get:Binds
    val CurrencyConversionRepositoryImpl.currencyConversionRepository: CurrencyConversionRepository

}