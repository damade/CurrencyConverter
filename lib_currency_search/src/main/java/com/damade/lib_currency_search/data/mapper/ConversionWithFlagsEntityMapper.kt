package com.damade.lib_currency_search.data.mapper

import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import javax.inject.Inject

internal class ConversionWithFlagsEntityMapper @Inject constructor() {

    fun mapFromEntity(entity: ConversionWithFlagsEntity): ConversionWithFlags {
        with(entity) {
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

    fun mapFromConversionEntity(
        entity: ConversionEntity,
        fromCurrencyFlag: String,
        toCurrencyFlag: String,
    ): ConversionWithFlags {
        with(entity) {
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

    fun mapToEntity(domain: ConversionWithFlags): ConversionWithFlagsEntity {
        with(domain) {
            return ConversionWithFlagsEntity(
                from = fromCurrency,
                fromCurrencyFlag = fromCurrencyFlag,
                to = toCurrency,
                toCurrencyFlag = toCurrencyFlag,
                amount = amountOfCurrencyFrom,
                result = amountOfCurrencyTo,
                rate = rate
            )
        }
    }
}