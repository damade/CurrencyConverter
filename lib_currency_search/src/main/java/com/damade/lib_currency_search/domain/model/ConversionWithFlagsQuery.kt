package com.damade.lib_currency_search.domain.model

data class ConversionWithFlagsQuery (val from: String, val fromCurrencyFlag: String,
                                     val to: String, val toCurrencyFlag: String,
                                     val amount: Int)