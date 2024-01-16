package com.damilola.core.ext

import kotlin.reflect.KMutableProperty0

fun KMutableProperty0<Boolean>.toggle() {
    set(!get())
}