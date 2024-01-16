package com.damilola.core_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class CoroutinesDispatchersModule {
    /** TODO: This will be removed in the nearest future,
     * Avoid injecting CoroutineDispatcher into a class, as is it against Structured Concurrency
     * {@link https://developer.android.com/kotlin/coroutines/coroutines-best-practices#inject-dispatchers}
     * {@link https://code.cash.app/rx-to-coroutines-concepts-structured-concurrency}
     */

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

}

