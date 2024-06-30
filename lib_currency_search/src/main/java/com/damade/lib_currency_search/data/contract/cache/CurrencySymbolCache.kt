package com.damade.lib_currency_search.data.contract.cache

import com.damade.lib_currency_search.domain.model.Symbol

internal interface CurrencySymbolCache {
    suspend fun saveCurrencySymbol(symbol: List<Symbol>)
    suspend fun fetchCurrencySymbol(): List<Symbol>?
}
