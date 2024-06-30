package com.damilola.remote.ext

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.rx3.rxSingle
import com.damilola.core.model.ResponseMessage
import com.damilola.core.network.NetworkMiddleware
import com.damilola.remote.utils.parseException
import com.squareup.moshi.JsonAdapter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

// region Apollo Call Factory

fun <T : Query.Data> apolloCall(
    middleWares: List<NetworkMiddleware> = emptyList(),
    adapter: JsonAdapter<ResponseMessage>, apolloClient: ApolloClient,
    query: Query<T>,
): Observable<ApolloResponse<T>> {
    return runMiddleWaresWithObservables(middleWares = middleWares)
        ?: executeApolloCall(adapter, apolloClient, query)
}

// endregion

// region Apollo Call tightly coupled with RxJava

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

// endregion

/**
 * Iterate over all the [NetworkMiddleware] passed and return true if passes all validation.
 * @return []
 */
private fun <T : Query.Data> runMiddleWaresWithObservables(
    middleWares: List<NetworkMiddleware> = emptyList(),
): Observable<ApolloResponse<T>>? {
    if (middleWares.isEmpty()) return null
    return middleWares.find { !it.isValid() }?.failure?.let { Observable.error(it) }
}