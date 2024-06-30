package com.damade.lib_currency_search.cache.impl

import com.damade.lib_currency_search.cache.mapper.CurrencySymbolCacheModelMapper
import com.damade.lib_currency_search.data.contract.cache.CurrencySymbolCache
import com.damade.lib_currency_search.domain.model.Symbol
import com.damilola.cache.model.SymbolCacheModel
import com.damilola.cache.room.dao.SymbolDao
import javax.inject.Inject

internal class CurrencySymbolCacheImpl @Inject constructor(
    private val symbolDao: SymbolDao,
    private val currencySymbolCacheModelMapper: CurrencySymbolCacheModelMapper
) : CurrencySymbolCache {

    override suspend fun saveCurrencySymbol(symbol: List<Symbol>) {
        val symbolModels : List<SymbolCacheModel> = currencySymbolCacheModelMapper
            .mapListToModel(symbol)

        symbolDao.insertSymbols(symbolModels)
    }

    override suspend fun fetchCurrencySymbol(): List<Symbol> {
        val model: List<SymbolCacheModel> = symbolDao.getAllCurrencySymbols()
        return currencySymbolCacheModelMapper.mapListToDomain(model = model)
    }
}
