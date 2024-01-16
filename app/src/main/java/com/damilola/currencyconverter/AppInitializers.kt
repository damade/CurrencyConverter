package com.damilola.currencyconverter

import android.app.Application
import com.damilola.core_android.model.Initializable
import javax.inject.Inject

class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards Initializable>,
) {
    fun init(application: Application) {
        initializers.forEach { initializable -> initializable.init(application = application) }
    }
}