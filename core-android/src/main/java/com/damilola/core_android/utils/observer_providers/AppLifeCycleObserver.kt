package com.damilola.core_android.utils.observer_providers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.net.URISyntaxException
import javax.inject.Inject


//class AppLifeCycleObserver @Inject constructor(
//    private val eventService: EventService
//) : LifecycleObserver {
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun onEnterForeground() {
//        try {
//            eventService.onConnect()
//        } catch (e: URISyntaxException) {
//            e.printStackTrace()
//        }
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    fun onEnterBackground() {
//       eventService.disconnect()
//    }
//
//}
