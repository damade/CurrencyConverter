package com.damade.lib_currency_search.data.fakes

import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionWithFlagsCache
import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity

class FakeCurrencyConversionWithFlagsCache: CurrencyConversionWithFlagsCache {

    private val tempCache: MutableList<ConversionWithFlagsEntity> = arrayListOf()
    private val cache: List<ConversionWithFlagsEntity> = tempCache

    override suspend fun saveCurrencyConversion(conversionEntity: ConversionWithFlagsEntity) {
        tempCache.add(conversionEntity)
    }

    override suspend fun fetchCurrencyConversionHistory(): List<ConversionWithFlagsEntity> {
        return cache
    }

    override suspend fun clearCurrencyConversionHistory() {
        tempCache.clear()
    }

}