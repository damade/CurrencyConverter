package com.damilola.core.middleware

import com.damilola.core.network.NetworkMiddleware


interface MiddlewaresProducer {
    fun getAll(): List<NetworkMiddleware>
}