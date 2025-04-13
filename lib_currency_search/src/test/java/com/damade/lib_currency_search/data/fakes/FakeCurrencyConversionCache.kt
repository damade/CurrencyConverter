package com.damade.lib_currency_search.data.fakes

import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionCache
import com.damade.lib_currency_search.domain.model.Conversion

class FakeCurrencyConversionCache: CurrencyConversionCache {

    private val tempCache: MutableList<Conversion> = arrayListOf()
    private val cache: List<Conversion> = tempCache


    override suspend fun saveCurrencyConversion(conversion: Conversion) {
        tempCache.add(conversion)
    }

    override suspend fun fetchCurrencyConversionHistory(): List<Conversion> {
        return cache
    }

    override suspend fun clearCurrencyConversionHistory() {
        tempCache.clear()
    }

}