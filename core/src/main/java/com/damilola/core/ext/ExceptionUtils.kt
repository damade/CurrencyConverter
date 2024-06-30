package com.damilola.core.ext


private const val GENERAL_ERROR_MESSAGE = "An unknown error from an exception not handled caused an Unknown failure"
val Throwable.errorMessage: String
    get() = message ?: localizedMessage ?: GENERAL_ERROR_MESSAGE