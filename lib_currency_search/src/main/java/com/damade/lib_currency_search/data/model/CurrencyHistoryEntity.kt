package com.damade.lib_currency_search.data.model

data class CurrencyHistoryEntity(
    val base: String,
    val historical: Boolean,
    val date: String,
    val currency: String,
    val rate: Double?
)
