package com.damilola.currencyconverter

import android.app.Application
import com.damilola.currencyconverter.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CurrencyConverterApplication: Application() {

    @Inject lateinit var appInitializers: AppInitializers

    override fun onCreate() {
        super.onCreate()

        appInitializers.init(application = this)
    }

}