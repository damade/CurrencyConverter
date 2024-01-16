package com.damilola.core.ext

fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}