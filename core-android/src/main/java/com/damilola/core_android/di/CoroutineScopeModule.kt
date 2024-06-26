package com.damilola.core_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


/** TODO: This will be removed in the nearest future,
 * Avoid injecting CoroutineScope into a class, as is it against Structured Concurrency.
 * See [Coroutines Best Practices](https://developer.android.com/kotlin/coroutines/coroutines-best-practices#global-scope)
 * @see <a href="https://code.cash.app/rx-to-coroutines-concepts-structured-concurrency">Cash App Coroutine</a>
 */
@InstallIn(SingletonComponent::class)
@Module
class CoroutineScopeModule {
    @Singleton
    @ApplicationScope
    @Provides
    fun providesCoroutineScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Singleton
    @IoScope
    @Provides
    fun providesIoCoroutineScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
}