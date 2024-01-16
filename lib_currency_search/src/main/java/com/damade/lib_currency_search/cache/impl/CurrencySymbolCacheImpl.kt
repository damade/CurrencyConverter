package com.damade.lib_currency_search.cache.impl

import com.damade.lib_currency_search.cache.mapper.CurrencySymbolCacheModelMapper
import com.damade.lib_currency_search.data.contract.cache.CurrencySymbolCache
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damilola.cache.model.SymbolCacheModel
import com.damilola.cache.room.dao.SymbolDao
import javax.inject.Inject

internal class CurrencySymbolCacheImpl @Inject constructor(
    private val symbolDao: SymbolDao,
    private val currencySymbolCacheModelMapper: CurrencySymbolCacheModelMapper
) : CurrencySymbolCache {

    override suspend fun saveCurrencySymbol(symbolEntities: List<SymbolEntity>) {
        val symbolModels : List<SymbolCacheModel> = currencySymbolCacheModelMapper
            .mapListToModel(symbolEntities)

        symbolDao.insertSymbols(symbolModels)
    }

    override suspend fun fetchCurrencySymbol(): List<SymbolEntity>? {
        val model: List<SymbolCacheModel> = symbolDao.getAllCurrencySymbols()
        return model?.let { currencySymbolCacheModelMapper.mapListToEntity(it) }
    }
}
