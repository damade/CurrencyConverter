package com.damade.lib_currency_search.data.contract.cache

import com.damade.lib_currency_search.data.model.SymbolEntity

internal interface CurrencySymbolCache {
    suspend fun saveCurrencySymbol(symbolEntities: List<SymbolEntity>)
    suspend fun fetchCurrencySymbol(): List<SymbolEntity>?
}
