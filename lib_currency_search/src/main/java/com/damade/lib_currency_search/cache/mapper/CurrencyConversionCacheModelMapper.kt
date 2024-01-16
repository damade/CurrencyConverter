package com.damade.lib_currency_search.cache.mapper

import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.CurrencyConversionHistoryCacheModel
import javax.inject.Inject

class CurrencyConversionCacheModelMapper @Inject constructor() :
    CacheModelMapper<CurrencyConversionHistoryCacheModel, ConversionEntity>{

    @Throws(NumberFormatException::class)
    override fun mapToModel(entity: ConversionEntity): CurrencyConversionHistoryCacheModel {
        return CurrencyConversionHistoryCacheModel(
            from = entity.from,
            to = entity.to,
            amount = entity.amount,
            rate = entity.rate,
            result = entity.result
        )
    }

    override fun mapToEntity(model: CurrencyConversionHistoryCacheModel): ConversionEntity {
        return ConversionEntity(
            model.from,
            model.to,
            model.amount,
            model.result,
            model.rate
        )
    }

    override fun mapListToModel(entity: List<ConversionEntity>): List<CurrencyConversionHistoryCacheModel> {
       return entity.map { mapToModel(it) }
    }

    override fun mapListToEntity(model: List<CurrencyConversionHistoryCacheModel>): List<ConversionEntity> {
        return model.map { mapToEntity(it) }
    }
}