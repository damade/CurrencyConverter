package com.damilola.ft_currency.model

import androidx.compose.runtime.saveable.mapSaver

data class ConversionUi(
    val fromCurrency: String,
    val toCurrency: String,
    val amountOfCurrencyFrom: Int,
    val amountOfCurrencyTo: Double?,
    val rate: Double?,
)

val ConversionSaver = run {
    val fromCurrencyKey = "fromCurrency"
    val toCurrencyKey = "toCurrency"
    val amountOfCurrencyFromKey = "amountOfCurrencyFrom"
    val amountOfCurrencyToKey = "amountOfCurrencyTo"
    val rateKey = "rate"
    mapSaver(
        save = { mapOf(
            fromCurrencyKey to it.fromCurrency,
            toCurrencyKey to it.toCurrency,
            amountOfCurrencyFromKey to it.amountOfCurrencyFrom,
            amountOfCurrencyToKey to it.amountOfCurrencyTo,
            rateKey to it.rate
        ) },
        restore = {
            ConversionUi(
                fromCurrency = it[fromCurrencyKey] as String,
                toCurrency =  it[toCurrencyKey] as String,
                amountOfCurrencyFrom = it[amountOfCurrencyFromKey] as Int,
                amountOfCurrencyTo = it[amountOfCurrencyToKey] as Double?,
                rate = it[rateKey] as Double?
            ) }
    )
}