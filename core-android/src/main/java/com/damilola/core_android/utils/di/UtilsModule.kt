package com.damilola.core_android.utils.di

import com.damilola.core_android.model.Initializable
import com.damilola.core_android.utils.logging.LoggerInitializer
import com.damilola.core_android.utils.performance.LeakCanaryInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
internal interface UtilsModule {

    @get:Binds
    @get:IntoSet
    val LeakCanaryInitializer.bindLeakCanary: Initializable

    @get:Binds
    @get:IntoSet
    val LoggerInitializer.bindLogger: Initializable


    companion object {

    }
}