package com.damade.lib_currency_search.data.fakes

import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionWithFlagsCache
import com.damade.lib_currency_search.domain.model.ConversionWithFlags

class FakeCurrencyConversionWithFlagsCache: CurrencyConversionWithFlagsCache {

    private val tempCache: MutableList<ConversionWithFlags> = arrayListOf()
    private val cache: List<ConversionWithFlags> = tempCache

    override suspend fun saveCurrencyConversion(conversion: ConversionWithFlags) {
        tempCache.add(conversion)
    }

    override suspend fun fetchCurrencyConversionHistory(): List<ConversionWithFlags> {
        return cache
    }

    override suspend fun clearCurrencyConversionHistory() {
        tempCache.clear()
    }

}