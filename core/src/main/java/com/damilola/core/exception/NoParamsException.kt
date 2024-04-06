package com.damilola.core.exception

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * An exception thrown when a use case parameter is null
 */
class NoParamsException(errorMessage: String = NO_PARAM_MESSAGE) :
    IllegalArgumentException(errorMessage)

const val NO_PARAM_MESSAGE = "Your params cannot be null for this use case"

@OptIn(ExperimentalContracts::class)
fun <T : Any> requireParams(value: T?): T {
    contract {
        returns() implies (value != null)
    }

    if (value == null) {
        throw NoParamsException()
    } else {
        return value
    }
}