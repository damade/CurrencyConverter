package com.damilola.remote.network_factories


import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import javax.inject.Inject

class ApolloNetworkFactory @Inject constructor(private val client: OkHttpClient) {

    fun createApollo(url: String): ApolloClient {

        return ApolloClient.Builder()
            .serverUrl(url)
            .okHttpClient(client)
            .build()

    }

}