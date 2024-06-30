package com.damilola.core_android.di

import javax.inject.Qualifier

//region RxJava Schedulers (Dispatcher)
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RxDefaultScheduler

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RxIoScheduler

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RxComputationScheduler

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RxMainScheduler

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RxImmediateScheduler


// end region