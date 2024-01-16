package com.damade.lib_currency_search.domain.model

data class ConversionWithFlags(
    val fromCurrency: String,
    val fromCurrencyFlag: String,
    val toCurrency: String,
    val toCurrencyFlag: String,
    val amountOfCurrencyFrom: Int,
    val amountOfCurrencyTo: Double?,
    val rate: Double?,
)