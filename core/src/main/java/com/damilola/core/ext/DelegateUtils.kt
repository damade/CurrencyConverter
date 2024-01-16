package com.damilola.core.ext

fun <T> init(initializer: () -> T): Lazy<T> = lazy(mode = LazyThreadSafetyMode.NONE, initializer)