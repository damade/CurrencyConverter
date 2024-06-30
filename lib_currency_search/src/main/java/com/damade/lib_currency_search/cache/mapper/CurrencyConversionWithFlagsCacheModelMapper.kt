package com.damade.lib_currency_search.cache.mapper

import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damilola.cache.mapper.CacheModelMapper
import com.damilola.cache.model.ConversionHistoryWithFlagsCacheModel
import javax.inject.Inject

class CurrencyConversionWithFlagsCacheModelMapper @Inject constructor() :
    CacheModelMapper<ConversionHistoryWithFlagsCacheModel, ConversionWithFlags> {

    @Throws(NumberFormatException::class)
    override fun mapToModel(domain: ConversionWithFlags): ConversionHistoryWithFlagsCacheModel {
        with(domain) {
            return ConversionHistoryWithFlagsCacheModel(
                from = fromCurrency,
                fromCurrencyFlag = fromCurrencyFlag,
                to = toCurrency,
                toCurrencyFlag = toCurrencyFlag,
                amount = amountOfCurrencyFrom,
                rate = rate,
                result = amountOfCurrencyTo,

                )
        }

    }

    override fun mapToDomain(model: ConversionHistoryWithFlagsCacheModel): ConversionWithFlags {
        with(model) {
            return ConversionWithFlags(
                fromCurrency = from,
                fromCurrencyFlag = fromCurrencyFlag,
                toCurrency = to,
                toCurrencyFlag = toCurrencyFlag,
                amountOfCurrencyFrom = amount,
                amountOfCurrencyTo = result,
                rate = rate
            )
        }
    }

    override fun mapListToModel(domain: List<ConversionWithFlags>): List<ConversionHistoryWithFlagsCacheModel> {
        return domain.map { mapToModel(it) }
    }

    override fun mapListToDomain(model: List<ConversionHistoryWithFlagsCacheModel>): List<ConversionWithFlags> {
        return model.map { mapToDomain(it) }
    }
}