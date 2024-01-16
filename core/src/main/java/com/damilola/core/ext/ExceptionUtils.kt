package com.damilola.core.ext

val Throwable.errorMessage: String
    get() = message ?: localizedMessage ?: "An error occurred"