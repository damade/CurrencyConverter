package com.damilola.core_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


/** TODO: This will be removed in the nearest future,
 * Avoid injecting CoroutineDispatcher as a property into a class, as is it against Structured Concurrency
 * @see <a href="https://developer.android.com/kotlin/coroutines/coroutines-best-practices#inject-dispatchers">Coroutines Best Practices</a>
 * @see <a href="https://code.cash.app/rx-to-coroutines-concepts-structured-concurrency">Cash App Coroutine</a>
 */
@InstallIn(SingletonComponent::class)
@Module
class CoroutinesDispatchersModule {

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

