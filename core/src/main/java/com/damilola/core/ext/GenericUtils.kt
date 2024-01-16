package com.damilola.core.ext

inline fun <reified T> genericCastOrNull(anything: Any):T? {
    return anything as? T
}

inline fun <reified T> modelConverter(anything: Any?):T? {
    return anything?.let { genericCastOrNull<T>(it) }
}