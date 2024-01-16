package com.damade.lib_currency_search.cache.mapper

import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.SymbolCacheModel
import javax.inject.Inject

internal class CurrencySymbolCacheModelMapper @Inject constructor() :
    CacheModelMapper<SymbolCacheModel, SymbolEntity> {

    override fun mapToModel(entity: SymbolEntity): SymbolCacheModel {
        return SymbolCacheModel(
            currencyCode = entity.code,
            currencyDescription = entity.description
        )
    }

    override fun mapToEntity(model: SymbolCacheModel): SymbolEntity {
        return SymbolEntity(
            code = model.currencyCode,
            description = model.currencyDescription
        )
    }

    override fun mapListToModel(entity: List<SymbolEntity>): List<SymbolCacheModel> {
        return entity.map { mapToModel(it) }
    }

    override fun mapListToEntity(model: List<SymbolCacheModel>): List<SymbolEntity> {
        return model.map { mapToEntity(it) }
    }
}