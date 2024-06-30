package com.damade.lib_currency_search.remote.model.response

internal data class CurrencyHistoryResponse(
    val success: Boolean,
    val rates: Map<String, Double>,
    val base: String,
    val historical: Boolean,
    val date: String,
)