package com.damade.lib_currency.cache.di

import com.damade.lib_currency.cache.impl.SymbolFlagCacheImpl
import com.damade.lib_currency.data.contract.cache.SymbolFlagCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
internal interface CacheModule {

    @get:Binds
    val SymbolFlagCacheImpl.symbolFlagCache: SymbolFlagCache
}
