package com.damilola.remote.graphqlremotes.libCurrency.impl

import com.apollographql.apollo3.ApolloClient
import com.damilola.remote.graphqlremotes.libCurrency.SymbolFlagsRemoteController
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.core.middleware.MiddlewaresProducer
import com.damilola.core.model.ResponseMessage
import com.damilola.remote.ext.apolloCall
import com.damilola.remote.graphqlremotes.libCurrency.model.SymbolFlagResponse
import com.squareup.moshi.JsonAdapter
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import query.CurrenciesAndroidQuery

class SymbolFlagsRemoteControllerImpl @Inject constructor(
    private val middlewareProvider: MiddlewaresProducer,
    private val errorAdapter: JsonAdapter<ResponseMessage>,
    private val apolloClient: ApolloClient
) : SymbolFlagsRemoteController {

    override fun fetchCurrencyWithFlags(): Observable<List<SymbolFlagResponse>> {
        return apolloCall(
            middleWares = middlewareProvider.getAll(),
            adapter = errorAdapter,
            apolloClient = apolloClient,
            query = CurrenciesAndroidQuery()
        ).map { response ->
            if (response.data?.countries != null && response.data?.countries.isNotNullOrEmpty()) {
                groupCountriesByCurrencies(response.data?.countries as List<CurrenciesAndroidQuery.Country>)
            } else {
                throw Throwable(message = "Currencies Flag GraphQL Error")
            }
        }
    }


    private fun groupCountriesByCurrencies(remoteModel: List<CurrenciesAndroidQuery.Country>): List<SymbolFlagResponse>
    {

        val groupedCountriesList: Map<String?, List<CurrenciesAndroidQuery.Country>> = remoteModel.groupBy {
            it.currency?.split(",")?.get(0)
        }

        val symbolFlagResponses = mutableListOf<SymbolFlagResponse>()
        for (currencyCode: String? in groupedCountriesList.keys) {
            if (currencyCode != null && currencyCode.isNotNullOrEmpty()) {
                val countries = groupedCountriesList[currencyCode]

                val eachSymbolFlagResponse = SymbolFlagResponse(
                    remoteCurrencyCode = currencyCode,
                    remoteCurrencyFlags = when (currencyCode) {
                        "EUR" -> {
                            "🇪🇺"
                        }
                        "USD" -> {
                            "🇺🇸"
                        }
                        else -> {
                            countries?.get(0)?.emoji ?: "🏴‍☠️"
                        }
                    },
                    remoteCurrencyFlagsUtF = when(currencyCode){
                        "EUR" -> {
                            "\uD83C\uDDEA\uD83C\uDDFA"
                        }
                        "USD" -> {
                            "\uD83C\uDDFA\uD83C\uDDF8"
                        }
                        else -> {
                            countries?.get(0)?.emojiU ?: "\uD83C\uDFF4\u200D☠️️"
                        }
                    }
                )
                symbolFlagResponses.add(eachSymbolFlagResponse)
            }

        }

        return symbolFlagResponses
    }

}
