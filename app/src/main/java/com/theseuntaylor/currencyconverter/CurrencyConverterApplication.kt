package com.theseuntaylor.currencyconverter

import android.app.Application
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