package com.damilola.core_android.utils.performance

import android.app.Application
import com.damilola.core_android.BuildConfig
import com.damilola.core_android.model.Initializable
import leakcanary.LeakCanary
import javax.inject.Inject

class LeakCanaryInitializer @Inject constructor() : Initializable {
    override fun init(application: Application) {
        val isEnable = BuildConfig.DEBUG
        LeakCanary.config = LeakCanary.config.copy(dumpHeap = isEnable)
        LeakCanary.showLeakDisplayActivityLauncherIcon(isEnable)

        // This log is added just to suppress kotlin unused variable lint warning and this will never be logger.
        logcat.logcat(tag = "Demo Application", message = { "Leak canary is disabled - State isEnable - $isEnable" })
    }

}