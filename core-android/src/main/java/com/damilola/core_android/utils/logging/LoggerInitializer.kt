package com.damilola.core_android.utils.logging

import android.app.Application
import com.damilola.core_android.BuildConfig
import com.damilola.core_android.model.Initializable
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import javax.inject.Inject

class LoggerInitializer @Inject constructor() : Initializable {
    override fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            // Log all priorities in debug builds, no-op in release builds.
            AndroidLogcatLogger.installOnDebuggableApp(application = application, minPriority = LogPriority.VERBOSE)
        }
    }

}