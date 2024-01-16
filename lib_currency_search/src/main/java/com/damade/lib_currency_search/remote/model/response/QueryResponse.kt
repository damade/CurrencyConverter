package com.damade.lib_currency_search.remote.model.response

internal data class QueryResponse(
    val from: String,
    val to: String,
    val amount: Int,
)