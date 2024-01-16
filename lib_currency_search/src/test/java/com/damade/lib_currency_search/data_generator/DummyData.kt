package com.damade.lib_currency_search.data_generator

import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.domain.model.*

internal object DummyData {

    const val from = "GBP"
    const val fromCurrencyFlag = "🇬🇧"
    const val to = "NGN"
    const val toCurrencyFlag = "🇳🇬"
    const val amount = 10

    val query = ConversionQuery(
        from,
        to,
        amount
    )

    val queryWithFlags = ConversionWithFlagsQuery(
        from = from,
        fromCurrencyFlag = fromCurrencyFlag,
        to = to,
        toCurrencyFlag = toCurrencyFlag,
        amount = amount
    )

    val symbol = Symbol(
        "Nigerian Currency",
        "NGN"
    )

    val conversion = Conversion(
        "GBP",
        "NGN",
        10,
        5000.0,
        500.0
    )

    private val conversionNew = Conversion(
        "USD",
        "NGN",
        100,
        40000.0,
        400.0
    )


    val symbolEntity = SymbolEntity(
        "Nigerian Currency",
        "NGN"
    )


    val conversionEntity = ConversionEntity(
        "GBP",
        "NGN",
        10,
        5000.0,
        500.0
    )

    val conversionWithFlagsEntity = ConversionWithFlagsEntity(
        from = "GBP",
        fromCurrencyFlag = "🇬🇧",
        to = "NGN",
        toCurrencyFlag = "🇳🇬",
        amount = 10,
        result = 5000.0,
        rate = 500.0
    )

    val gbpToNgnConversionWithFlags = ConversionWithFlags(
        fromCurrency = "GBP",
        fromCurrencyFlag = "🇬🇧",
        toCurrency = "NGN",
        toCurrencyFlag = "🇳🇬",
        amountOfCurrencyFrom = 10,
        amountOfCurrencyTo = 5000.0,
        rate = 500.0
    )

    private val usdToNgnConversionWithFlags = ConversionWithFlags(
        fromCurrency = "USD",
        fromCurrencyFlag = "🇺🇸",
        toCurrency = "NGN",
        toCurrencyFlag = "🇳🇬",
        amountOfCurrencyFrom = 100,
        amountOfCurrencyTo = 40000.0,
        rate = 400.0
    )

    val conversionHistory = listOf(conversion, conversionNew)

    val conversionWithFlagsHistory = listOf(gbpToNgnConversionWithFlags, usdToNgnConversionWithFlags)


}