package com.damilola.core_android.di

import com.damilola.config.AppsConfig
import com.damilola.config.BuildTypeDebug
import com.damilola.config.BuildTypeRelease
import com.damilola.core_android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppConfigModule {
    @Provides
    fun provideAppConfig(): AppsConfig = if (BuildConfig.DEBUG) {
        BuildTypeDebug
    } else {
        BuildTypeRelease
    }
}