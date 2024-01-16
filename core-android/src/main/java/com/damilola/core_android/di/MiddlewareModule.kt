package com.damilola.core_android.di

import com.damilola.core_android.middleware.MiddlewareProducerImpl
import com.damilola.core_android.network.ConnectivityMiddleware
import com.damilola.core_android.utils.resource_providers.ResourceProvider
import com.damilola.core_android.utils.resource_providers.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MiddlewareModule {

    @Singleton
    @Binds
    abstract fun bindResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider


    companion object{
        @Provides
        @Singleton
        fun bindMiddlewareProvider(
            connectivityMiddlewareImpl:
            ConnectivityMiddleware
        ): com.damilola.core.middleware.MiddlewaresProducer =
            MiddlewareProducerImpl.Builder()
                .add(middleware = connectivityMiddlewareImpl)
                .build()
    }

}