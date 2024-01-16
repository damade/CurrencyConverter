package com.damade.lib_currency_search.cache.di

import com.damade.lib_currency_search.cache.impl.CurrencyConversionCacheImpl
import com.damade.lib_currency_search.cache.impl.CurrencyConversionWithFlagsCacheImpl
import com.damade.lib_currency_search.cache.impl.CurrencySymbolCacheImpl
import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionCache
import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionWithFlagsCache
import com.damade.lib_currency_search.data.contract.cache.CurrencySymbolCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface CacheModule {

    @get:Binds
    val CurrencyConversionCacheImpl.currencyConversionCache : CurrencyConversionCache

    @get:Binds
    val CurrencySymbolCacheImpl.currencySymbolCache : CurrencySymbolCache

    @get:Binds
    val CurrencyConversionWithFlagsCacheImpl.currencyConversionWithFlagsCache : CurrencyConversionWithFlagsCache
}