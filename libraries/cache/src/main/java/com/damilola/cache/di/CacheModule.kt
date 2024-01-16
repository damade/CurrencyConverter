package com.damilola.cache.di

import android.content.Context
import com.damilola.cache.room.AppDatabase
import com.damilola.cache.room.dao.*
import com.damilola.config.AppsConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object CacheModule {
    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context, appsConfig: AppsConfig): AppDatabase {
        return AppDatabase.build(context, appsConfig.DB_NAME)
    }

    @[Provides Singleton]
    fun provideSymbolDao(appDatabase: AppDatabase): SymbolDao {
        return appDatabase.symbolDao
    }

    @[Provides Singleton]
    fun provideSymbolFlagDao(appDatabase: AppDatabase): SymbolFlagDao {
        return appDatabase.symbolFlagDao
    }

    @[Provides Singleton]
    fun provideCurrencyConversionDao(appDatabase: AppDatabase): CurrencyConversionHistoryDao {
        return appDatabase.currencyConversionHistoryDao
    }

    @[Provides Singleton]
    fun provideConversionHistoryWithFlagsDao(appDatabase: AppDatabase): ConversionHistoryWithFlagsDao {
        return appDatabase.conversionHistoryWithFlagsDao
    }

    @[Provides Singleton]
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeysDao{
        return appDatabase.remoteKeysDao
    }


}