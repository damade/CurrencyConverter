package com.damilola.core_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

@InstallIn(SingletonComponent::class)
@Module
class RxJavaSchedulersModule {

    @RxDefaultScheduler
    @Provides
    fun providesDefaultScheduler(): Scheduler = Schedulers.single()

    @RxIoScheduler
    @Provides
    fun providesIoDispatcher(): Scheduler = Schedulers.io()

    @RxMainScheduler
    @Provides
    fun providesMainDispatcher(): Scheduler = AndroidSchedulers.mainThread()

    @RxImmediateScheduler
    @Provides
    fun providesMainImmediateDispatcher(): Scheduler = Schedulers.trampoline()

    @RxComputationScheduler
    @Provides
    fun providesComputationDispatcher(): Scheduler = Schedulers.computation()

}
