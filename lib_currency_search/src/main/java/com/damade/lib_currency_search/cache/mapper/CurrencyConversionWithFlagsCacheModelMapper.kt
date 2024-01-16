package com.damade.lib_currency_search.cache.mapper

import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.ConversionHistoryWithFlagsCacheModel
import javax.inject.Inject

class CurrencyConversionWithFlagsCacheModelMapper @Inject constructor() :
    CacheModelMapper<ConversionHistoryWithFlagsCacheModel, ConversionWithFlagsEntity>{

    @Throws(NumberFormatException::class)
    override fun mapToModel(entity: ConversionWithFlagsEntity): ConversionHistoryWithFlagsCacheModel {
        with(entity){
            return ConversionHistoryWithFlagsCacheModel(
                from = from,
                fromCurrencyFlag = fromCurrencyFlag,
                to = to,
                toCurrencyFlag = toCurrencyFlag,
                amount = amount,
                rate = rate,
                result = result

            )
        }

    }

    override fun mapToEntity(model: ConversionHistoryWithFlagsCacheModel): ConversionWithFlagsEntity {
        with(model){
            return ConversionWithFlagsEntity(
                from = from,
                fromCurrencyFlag = fromCurrencyFlag,
                to = to,
                toCurrencyFlag = toCurrencyFlag,
                amount = amount,
                result = result,
                rate = rate
            )
        }
    }

    override fun mapListToModel(entity: List<ConversionWithFlagsEntity>): List<ConversionHistoryWithFlagsCacheModel> {
       return entity.map { mapToModel(it) }
    }

    override fun mapListToEntity(model: List<ConversionHistoryWithFlagsCacheModel>): List<ConversionWithFlagsEntity> {
        return model.map { mapToEntity(it) }
    }
}