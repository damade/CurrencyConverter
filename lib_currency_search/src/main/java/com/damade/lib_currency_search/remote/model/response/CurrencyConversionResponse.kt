package com.damade.lib_currency_search.remote.model.response

internal data class CurrencyConversionResponse(
    val motd: MotdResponse,
    val success: Boolean,
    val query: QueryResponse,
    val info: InfoResponse,
    val historical: Boolean,
    val date: String,
    val result: Double?
)