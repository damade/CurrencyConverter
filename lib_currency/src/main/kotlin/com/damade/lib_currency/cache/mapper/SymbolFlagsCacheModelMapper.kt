package com.damade.lib_currency.cache.mapper

import com.damade.lib_currency.domain.model.SymbolFlag
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.SymbolFlagCacheModel
import javax.inject.Inject

internal class SymbolFlagsCacheModelMapper @Inject constructor() :
    CacheModelMapper<SymbolFlagCacheModel, SymbolFlag> {

    override fun mapToModel(domain: SymbolFlag): SymbolFlagCacheModel {
        return with(domain) {
            SymbolFlagCacheModel(
                currencyCode = currencyCode,
                currencyFlag = currencyFlag,
                currencyFlagUtf = currencyFlagUtf,
            )
        }
    }

    override fun mapToDomain(model: SymbolFlagCacheModel): SymbolFlag {
        return with(model) {
            SymbolFlag(
                currencyCode = currencyCode,
                currencyFlag = currencyFlag,
                currencyFlagUtf = currencyFlagUtf,
            )
        }
    }

    override fun mapListToModel(domain: List<SymbolFlag>): List<SymbolFlagCacheModel> {
        return domain.map(::mapToModel)
    }

    override fun mapListToDomain(model: List<SymbolFlagCacheModel>): List<SymbolFlag> {
        return model.map(::mapToDomain)
    }
}