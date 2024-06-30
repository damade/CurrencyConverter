package com.damade.lib_currency_search.remote.model.response

internal data class CurrencyConversionResponse(
    val success: Boolean,
    val query: QueryResponse,
    val info: InfoResponse,
    val result: Double?,
)