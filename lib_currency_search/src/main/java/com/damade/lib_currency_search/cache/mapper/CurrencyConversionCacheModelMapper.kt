package com.damade.lib_currency_search.cache.mapper

import com.damade.lib_currency_search.domain.model.Conversion
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.CurrencyConversionHistoryCacheModel
import javax.inject.Inject

class CurrencyConversionCacheModelMapper @Inject constructor() :
    CacheModelMapper<CurrencyConversionHistoryCacheModel, Conversion>{

    @Throws(NumberFormatException::class)
    override fun mapToModel(domain: Conversion): CurrencyConversionHistoryCacheModel {
        return CurrencyConversionHistoryCacheModel(
            from = domain.from,
            to = domain.to,
            amount = domain.amount,
            rate = domain.rate,
            result = domain.result
        )
    }

    override fun mapToDomain(model: CurrencyConversionHistoryCacheModel): Conversion{
        return Conversion(
            from = model.from,
            to = model.to,
            amount = model.amount,
            result = model.result,
            rate = model.rate,
        )
    }

    override fun mapListToModel(domain: List<Conversion>): List<CurrencyConversionHistoryCacheModel> {
       return domain.map { mapToModel(it) }
    }

    override fun mapListToDomain(model: List<CurrencyConversionHistoryCacheModel>): List<Conversion> {
        return model.map { mapToDomain(it) }
    }
}