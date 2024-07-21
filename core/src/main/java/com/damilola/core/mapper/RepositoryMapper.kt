package com.damilola.core.mapper

import com.damilola.core.model.Either
import com.damilola.core.model.EitherFailure
import com.damilola.core.model.Failure

suspend inline fun <In, Out> Either<Failure, In?>.mapData(
    entityMapper: (In) -> Out,
    crossinline cacheStorage: suspend (Out) -> Unit,
    noinline cacheFetch: (suspend () -> Out?)? = null,
): Out {
    if (this.isSuccess) {
        val networkEntity = this.getSuccessOrNull()
        if (networkEntity != null) {
            val domainEntity = entityMapper(networkEntity)
            cacheStorage(domainEntity)
            return domainEntity
        } else {
            throw this.getFailureOrNull() ?: EitherFailure
        }
    } else {
        cacheFetch ?: throw this.getFailureOrNull() ?: EitherFailure
        val cachedEntity = cacheFetch()
        if (cachedEntity != null) {
            return cachedEntity
        } else {
            throw this.getFailureOrNull() ?: EitherFailure
        }
    }
}