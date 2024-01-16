package com.damilola.remote.graphqlremotes.libCurrency.model

data class SymbolFlagResponse(
    val remoteCurrencyCode: String,
    val remoteCurrencyFlags: String,
    val remoteCurrencyFlagsUtF: String
)
