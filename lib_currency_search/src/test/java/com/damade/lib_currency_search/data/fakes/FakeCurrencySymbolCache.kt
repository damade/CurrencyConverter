package com.damade.lib_currency_search.data.fakes

import com.damade.lib_currency_search.data.contract.cache.CurrencySymbolCache
import com.damade.lib_currency_search.data.model.SymbolEntity

class FakeCurrencySymbolCache: CurrencySymbolCache {

    private val tempCache: MutableList<SymbolEntity> = arrayListOf()
    private val cache: List<SymbolEntity> = tempCache

    override suspend fun saveCurrencySymbol(symbolEntities: List<SymbolEntity>) {
            clearCache()
            tempCache.addAll(symbolEntities)
    }

    override suspend fun fetchCurrencySymbol(): List<SymbolEntity> {
        return cache
    }

    internal fun clearCache() {
        tempCache.clear()
    }

}