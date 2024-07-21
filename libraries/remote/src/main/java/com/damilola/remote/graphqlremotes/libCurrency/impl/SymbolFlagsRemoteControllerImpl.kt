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
    private val apolloClient: ApolloClient,
) : SymbolFlagsRemoteController {

    override fun fetchCurrencyWithFlags(): Observable<List<SymbolFlagResponse>> {
        return apolloCall(
            middleWares = middlewareProvider.getAll(),
            adapter = errorAdapter,
            apolloClient = apolloClient,
            query = CurrenciesAndroidQuery()
        ).map { response ->
            if (response.data?.countries != null && response.data?.countries.isNotNullOrEmpty()) {
                mapCountriesToCurrencies(
                    remoteModel = response.data?.countries.orEmpty()
                )
            } else {
                throw Throwable(message = "Currencies Flag GraphQL Error")
            }
        }
    }


    private fun mapCountriesToCurrencies(
        remoteModel: List<CurrenciesAndroidQuery.Country?>,
    ): List<SymbolFlagResponse> {
        val groupedCountriesList: Map<String, List<CurrenciesAndroidQuery.Country>> = remoteModel
            .mapNotNull { it }
            .groupBy {
                it.currency?.split(",")?.get(0).orEmpty()
            }

        val symbolFlagResponses = mutableListOf<SymbolFlagResponse>()
        for (currencyCode: String in groupedCountriesList.keys) {
            if (currencyCode.isNotNullOrEmpty()) {
                val countries = groupedCountriesList[currencyCode].orEmpty()

                val eachSymbolFlagResponse = SymbolFlagResponse(
                    remoteCurrencyCode = currencyCode,
                    remoteCurrencyFlags = mapCurrencyFlag(
                        currencyCode = currencyCode,
                        countryEmoji = countries.firstOrNull()?.emoji,
                    ),
                    remoteCurrencyFlagsUtF = mapCurrencyFlagUtf(
                        currencyCode = currencyCode,
                        countryEmojiUtf = countries.firstOrNull()?.emojiU,
                    )
                )
                symbolFlagResponses.add(eachSymbolFlagResponse)
            }
        }
        return symbolFlagResponses
    }

    private fun mapCurrencyFlag(
        currencyCode: String,
        countryEmoji: String?,
    ): String = when (currencyCode) {
        "EUR" -> {
            "🇪🇺"
        }

        "USD" -> {
            "🇺🇸"
        }

        else -> {
            countryEmoji ?: "🏴‍☠️"
        }
    }

    private fun mapCurrencyFlagUtf(
        currencyCode: String,
        countryEmojiUtf: String?,
    ): String = when (currencyCode) {
        "EUR" -> {
            "\uD83C\uDDEA\uD83C\uDDFA"
        }

        "USD" -> {
            "\uD83C\uDDFA\uD83C\uDDF8"
        }

        else -> {
            countryEmojiUtf ?: "\uD83C\uDFF4\u200D☠️️"
        }
    }
}
