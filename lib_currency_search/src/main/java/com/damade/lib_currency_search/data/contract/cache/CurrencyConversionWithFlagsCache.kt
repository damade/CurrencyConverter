package com.damade.lib_currency_search.data.contract.cache

import com.damade.lib_currency_search.domain.model.ConversionWithFlags

internal interface CurrencyConversionWithFlagsCache {
    suspend fun saveCurrencyConversion(conversion: ConversionWithFlags)
    suspend fun fetchCurrencyConversionHistory(): List<ConversionWithFlags>
    suspend fun clearCurrencyConversionHistory()
}
