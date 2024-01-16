package com.damilola.core.network

import com.damilola.core.model.NetworkMiddlewareFailure

abstract class NetworkMiddleware {

    abstract val failure: NetworkMiddlewareFailure

    abstract fun isValid(): Boolean
}