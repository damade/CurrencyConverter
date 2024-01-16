package com.damade.lib_currency.cache.impl

import com.damade.lib_currency.cache.mapper.SymbolFlagsCacheModelMapper
import com.damade.lib_currency.data.contract.cache.SymbolFlagCache
import com.damade.lib_currency.data.model.SymbolFlagEntity
import com.damilola.cache.model.SymbolFlagCacheModel
import com.damilola.cache.room.dao.SymbolFlagDao
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

internal class SymbolFlagCacheImpl @Inject constructor(
    private val symbolFlagDao: SymbolFlagDao,
    private val symbolFlagsCacheModelMapper: SymbolFlagsCacheModelMapper
) : SymbolFlagCache {

    override fun saveCurrencySymbolFlag(symbolFlagEntities: List<SymbolFlagEntity>) {
        val symbolModels : List<SymbolFlagCacheModel> = symbolFlagsCacheModelMapper
            .mapListToModel(symbolFlagEntities)

        symbolFlagDao.insertSymbolsWithFlag(symbolModels)
    }

    override fun fetchCurrencySymbolFlagWithObservable(): Observable<List<SymbolFlagEntity>> {

         return symbolFlagDao.getAllCurrencySymbolsWithFlagObservable()
                .map {
                    symbolFlagsCacheModelMapper.mapListToEntity(it)
                }

    }

    override fun fetchCurrencySymbolFlag(): List<SymbolFlagEntity>? {
        return symbolFlagDao.getAllCurrencySymbolsWithFlag()?.let {
            symbolFlagsCacheModelMapper.mapListToEntity(it)
        }

    }
}
