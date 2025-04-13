package com.damade.lib_currency_search.data.fakes

import com.damade.lib_currency_search.data.contract.cache.CurrencySymbolCache
import com.damade.lib_currency_search.domain.model.Symbol

class FakeCurrencySymbolCache: CurrencySymbolCache {

    private val tempCache: MutableList<Symbol> = arrayListOf()
    private val cache: List<Symbol> = tempCache

    override suspend fun saveCurrencySymbol(symbol: List<Symbol>) {
            clearCache()
            tempCache.addAll(symbol)
    }

    override suspend fun fetchCurrencySymbol(): List<Symbol> {
        return cache
    }

    internal fun clearCache() {
        tempCache.clear()
    }

}