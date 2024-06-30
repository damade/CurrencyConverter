package com.damade.lib_currency.cache.impl

import com.damade.lib_currency.cache.mapper.SymbolFlagsCacheModelMapper
import com.damade.lib_currency.data.contract.cache.SymbolFlagCache
import com.damade.lib_currency.domain.model.SymbolFlag
import com.damilola.cache.model.SymbolFlagCacheModel
import com.damilola.cache.room.dao.SymbolFlagDao
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

internal class SymbolFlagCacheImpl @Inject constructor(
    private val symbolFlagDao: SymbolFlagDao,
    private val symbolFlagsCacheModelMapper: SymbolFlagsCacheModelMapper,
) : SymbolFlagCache {

    override fun saveCurrencySymbolFlag(symbolFlag: List<SymbolFlag>) {
        val symbolModels: List<SymbolFlagCacheModel> = symbolFlagsCacheModelMapper
            .mapListToModel(domain = symbolFlag)

        symbolFlagDao.insertSymbolsWithFlag(symbols = symbolModels)
    }

    override fun fetchCurrencySymbolFlagWithObservable(): Observable<List<SymbolFlag>> {

        return symbolFlagDao.getAllCurrencySymbolsWithFlagObservable()
            .map {
                symbolFlagsCacheModelMapper.mapListToDomain(model = it)
            }

    }

    override fun fetchCurrencySymbolFlag(): List<SymbolFlag>? {
        return symbolFlagDao.getAllCurrencySymbolsWithFlag()?.let {
            symbolFlagsCacheModelMapper.mapListToDomain(model = it)
        }

    }
}
