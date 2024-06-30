package com.damade.lib_currency_search.data.contract.cache

import com.damade.lib_currency_search.domain.model.Conversion

internal interface CurrencyConversionCache {
    suspend fun saveCurrencyConversion(conversion: Conversion)
    suspend fun fetchCurrencyConversionHistory(): List<Conversion>
    suspend fun clearCurrencyConversionHistory()
}
