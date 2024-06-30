package com.damilola.core_android.middleware

import com.damilola.core.middleware.MiddlewaresProducer
import com.damilola.core.network.NetworkMiddleware


class MiddlewareProducerImpl : MiddlewaresProducer {

    private val middlewareList: MutableList<NetworkMiddleware> = mutableListOf()

    fun add(middleware: NetworkMiddleware): MiddlewaresProducer {
        this.middlewareList.add(middleware)
        return this
    }


    override fun getAll(): List<NetworkMiddleware> = middlewareList
}