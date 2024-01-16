package com.damade.lib_currency_search.data.model

data class ConversionWithFlagsEntity(
    val from: String,
    val fromCurrencyFlag: String,
    val to: String,
    val toCurrencyFlag: String,
    val amount: Int,
    val result: Double?,
    val rate: Double?
)
