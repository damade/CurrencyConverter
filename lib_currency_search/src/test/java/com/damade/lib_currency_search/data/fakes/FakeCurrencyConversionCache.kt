package com.damade.lib_currency_search.data.fakes

import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionCache
import com.damade.lib_currency_search.data.model.ConversionEntity

class FakeCurrencyConversionCache: CurrencyConversionCache {

    private val tempCache: MutableList<ConversionEntity> = arrayListOf()
    private val cache: List<ConversionEntity> = tempCache


    override suspend fun saveCurrencyConversion(conversionEntity: ConversionEntity) {
        tempCache.add(conversionEntity)
    }

    override suspend fun fetchCurrencyConversionHistory(): List<ConversionEntity> {
        return cache
    }

    override suspend fun clearCurrencyConversionHistory() {
        tempCache.clear()
    }

}