package com.damade.lib_currency_search.data.contract.cache

import com.damade.lib_currency_search.data.model.ConversionEntity

internal interface CurrencyConversionCache {
    suspend fun saveCurrencyConversion(conversionEntity: ConversionEntity)
    suspend fun fetchCurrencyConversionHistory(): List<ConversionEntity>
    suspend fun clearCurrencyConversionHistory()
}
