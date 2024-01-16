package com.damilola.core_android.di

import javax.inject.Qualifier


// region Coroutine Dispatcher and Scope Qualifiers
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoScope
// end region

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