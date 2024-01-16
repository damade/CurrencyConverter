package com.damilola.core.ext

fun Double.to2dp(): Double {
    return Math.round(this * 100.0) / 100.00
}

fun Double.to4dp(): Double {
    return Math.round(this * 10000.0) / 10000.00
}

fun Double?.orZero(): Double = this ?: Double.MIN_VALUE