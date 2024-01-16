package com.damilola.core_android.middleware

import com.damilola.core.middleware.MiddlewaresProducer
import com.damilola.core.network.NetworkMiddleware


class MiddlewareProducerImpl private constructor(
    private val middlewareList: List<NetworkMiddleware> = listOf()
) : MiddlewaresProducer {

    class Builder(
        private val middlewareList: MutableList<NetworkMiddleware> = mutableListOf()
    ) {

        fun add(middleware: NetworkMiddleware) = apply {
            this.middlewareList.add(middleware)
        }

        fun build() = MiddlewareProducerImpl(
            middlewareList = middlewareList
        )
    }


    override fun getAll(): List<NetworkMiddleware> = middlewareList
}