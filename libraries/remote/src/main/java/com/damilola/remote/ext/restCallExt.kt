package com.damilola.remote.ext


import com.damilola.core.ext.toError
import com.damilola.core.ext.toSuccess
import com.damilola.core.model.Either
import com.damilola.core.model.Failure
import com.damilola.core.model.ResponseMessage
import com.damilola.core.network.NetworkMiddleware
import com.damilola.remote.utils.parseException
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.withContext


// region Coroutine Call Factory

suspend inline fun <T> call(
    middleWares: List<NetworkMiddleware> = emptyList(),
    ioDispatcher: CoroutineDispatcher,
    adapter: JsonAdapter<ResponseMessage>,
    crossinline retrofitCall: suspend () -> T,
): Either<Failure, T> {
    return runMiddleWares(middleWares = middleWares)?.toError()
        ?: executeRetrofitCall(ioDispatcher, adapter, retrofitCall)
}

// endregion

// region Network call tightly coupled with Coroutine

suspend inline fun <T> executeRetrofitCall(
    ioDispatcher: CoroutineDispatcher,
    adapter: JsonAdapter<ResponseMessage>,
    crossinline retrofitCall: suspend () -> T,
): Either<Failure, T> {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable)
        throwable.parseException(adapter).toError()
    }

    return withContext(ioDispatcher + exceptionHandler) {
        try {
            return@withContext retrofitCall().toSuccess()
        } catch (e: Exception) {
            if (e !is CancellationException) {
                println(e)
                println("I got here")
                return@withContext e.parseException(adapter).toError()
            } else {
                throw e
            }
        }
    }
}

// end region

/**
 * Iterate over all the [NetworkMiddleware] and return true if all of them are valid.
 * @return []
 */
fun runMiddleWares(
    middleWares: List<NetworkMiddleware> = emptyList(),
): Failure? {
    if (middleWares.isEmpty()) return null
    return middleWares.find { !it.isValid() }?.failure
}