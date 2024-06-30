package com.damade.lib_currency_search.cache.mapper

import com.damade.lib_currency_search.domain.model.Symbol
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.SymbolCacheModel
import javax.inject.Inject

internal class CurrencySymbolCacheModelMapper @Inject constructor() :
    CacheModelMapper<SymbolCacheModel, Symbol> {

    override fun mapToModel(domain: Symbol): SymbolCacheModel {
        return SymbolCacheModel(
            currencyCode = domain.currencyCode,
            currencyDescription = domain.currencyDescription,
        )
    }

    override fun mapToDomain(model: SymbolCacheModel): Symbol {
        return Symbol(
            currencyCode = model.currencyCode,
            currencyDescription = model.currencyDescription,
        )
    }

    override fun mapListToModel(entity: List<Symbol>): List<SymbolCacheModel> {
        return entity.map(::mapToModel)
    }

    override fun mapListToDomain(model: List<SymbolCacheModel>): List<Symbol> {
        return model.map(::mapToDomain)
    }
}