package com.damade.lib_currency.cache.mapper

import com.damade.lib_currency.data.model.SymbolFlagEntity
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.SymbolFlagCacheModel
import javax.inject.Inject

internal class SymbolFlagsCacheModelMapper @Inject constructor() :
    CacheModelMapper<SymbolFlagCacheModel, SymbolFlagEntity> {

    override fun mapToModel(entity: SymbolFlagEntity): SymbolFlagCacheModel{
        return with(entity){
            SymbolFlagCacheModel(
                currencyCode = code,
                currencyFlag = flag,
                currencyFlagUtf = flagUtf
            )
        }
    }

    override fun mapToEntity(model: SymbolFlagCacheModel): SymbolFlagEntity{
        return with(model) {
            SymbolFlagEntity(
                code = currencyCode,
                flag = currencyFlag,
                flagUtf = currencyFlagUtf
            )
        }
    }

    override fun mapListToModel(entity: List<SymbolFlagEntity>): List<SymbolFlagCacheModel> {
        return entity.map { mapToModel(it) }
    }

    override fun mapListToEntity(model: List<SymbolFlagCacheModel>): List<SymbolFlagEntity> {
        return model.map { mapToEntity(it) }
    }
}