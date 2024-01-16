package com.damilola.core_android.model

import android.app.Application

/**
 * This uses Facade Pattern to abstract 3rd-Party Libraries
 * https://en.wikipedia.org/wiki/Facade_pattern
 */
interface Initializable {
    fun init(application: Application)
}