package com.damade.lib_currency_search.data.contract.cache

import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity

internal interface CurrencyConversionWithFlagsCache {
    suspend fun saveCurrencyConversion(conversionEntity: ConversionWithFlagsEntity)
    suspend fun fetchCurrencyConversionHistory(): List<ConversionWithFlagsEntity>
    suspend fun clearCurrencyConversionHistory()
}
