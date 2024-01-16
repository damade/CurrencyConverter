package com.damilola.remote.ext


import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.rx3.rxSingle
import com.damilola.core.ext.toError
import com.damilola.core.ext.toSuccess
import com.damilola.core.model.*
import com.damilola.core.network.NetworkMiddleware
import com.damilola.core.model.Either
import com.squareup.moshi.JsonAdapter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.BufferedSource
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException


//region Coroutine Call Factory
suspend inline fun <T> call(
    middleWares: List<NetworkMiddleware> = emptyList(),
    ioDispatcher: CoroutineDispatcher,
    adapter: JsonAdapter<ResponseMessage>,
    crossinline retrofitCall: suspend () -> T
): Either<Failure, T> {
    return runMiddleWares(middleWares = middleWares)?.toError()
        ?: executeRetrofitCall(ioDispatcher, adapter, retrofitCall)
}
//end region

//region Network call tightly coupled with Coroutine
suspend inline fun <T> executeRetrofitCall(
    ioDispatcher: CoroutineDispatcher,
    adapter: JsonAdapter<ResponseMessage>,
    crossinline retrofitCall: suspend () -> T
): Either<Failure, T> {
    return withContext(ioDispatcher) {
        try {
            return@withContext retrofitCall().toSuccess()
        } catch (e: Exception) {
            return@withContext e.parseException(adapter).toError()
        }
    }
}
// end region

//region Apollo Call Factory
fun <T : Query.Data> apolloCall(
    middleWares: List<NetworkMiddleware> = emptyList(),
    adapter: JsonAdapter<ResponseMessage>, apolloClient: ApolloClient,
    query: Query<T>,
): Observable<ApolloResponse<T>> {
    return runMiddleWaresWithObservables(middleWares = middleWares)
        ?: executeApolloCall(adapter, apolloClient, query)
}
// end region

//region Apollo Call tightly coupled with RxJava
private fun <T : Query.Data> executeApolloCall(
    adapter: JsonAdapter<ResponseMessage>,
    apolloClient: ApolloClient,
    query: Query<T>,
): Observable<ApolloResponse<T>> {
    return apolloClient
        .query(query)
        .rxSingle()
        .onErrorResumeNext {
            Single.error(it.parseException(adapter))
        }
        .toObservable()

}
// end region

/**
 * Iterate ove all the [NetworkMiddleware] and return true if all of them are valid.
 * @return []
 */
fun runMiddleWares(
    middleWares: List<NetworkMiddleware> = emptyList(),
): Failure? {
    if (middleWares.isEmpty()) return null
    return middleWares.find { !it.isValid() }?.failure
}

/**
 * Iterate ove all the [NetworkMiddleware] and return true if all of them are valid.
 * @return []
 */
fun <T : Query.Data> runMiddleWaresWithObservables(
    middleWares: List<NetworkMiddleware> = emptyList(),
): Observable<ApolloResponse<T>>? {
    if (middleWares.isEmpty()) return null
    return middleWares.find { !it.isValid() }?.failure?.let { Observable.error(it) }
}


fun Throwable.parseException(
    adapter: JsonAdapter<ResponseMessage>
): Failure {
    return when (this) {
        is SSLHandshakeException -> SSLError
        is SocketTimeoutException -> TimeOut
        is SSLException -> NetworkConnectionLostSuddenly
        is IOException -> Failure.UnexpectedFailure(failureMessage = this.message)
        is HttpException -> {
            val errorService = adapter.parseError(response()?.errorBody()?.source())
            if (errorService != null) {
                ServiceBodyFailure(
                    internalCode = errorService.code,
                    internalMessage = errorService.message
                )
            } else {
                Failure.UnexpectedFailure(
                    failureMessage = "Service ERROR BODY does not match."
                )
            }
        }
        else -> Failure.UnexpectedFailure(
            failureMessage = message ?: "Exception not handled caused an Unknown failure"
        )
    }
}

private fun JsonAdapter<ResponseMessage>.parseError(
    json: BufferedSource?
): ResponseMessage? {
    return if (json != null) {
        fromJson(json.toString())
    } else {
        null
    }
}