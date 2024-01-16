package com.damilola.ft_currency.mapper

import com.damade.lib_currency_search.domain.model.Conversion
import com.damilola.core.mapper.DomainMapper
import com.damilola.ft_currency.model.ConversionUi
import javax.inject.Inject

internal class ConversionDomainMapper @Inject constructor() :
    DomainMapper<Conversion, ConversionUi> {

    override fun mapFromDomain(domain: Conversion): ConversionUi {
        return ConversionUi(
            amountOfCurrencyTo = domain.result,
            rate =  domain.rate,
            fromCurrency = domain.from,
            toCurrency = domain.to,
            amountOfCurrencyFrom = domain.amount
        )
    }

    override fun mapToDomain(uiModel: ConversionUi): Conversion {
        return Conversion(
            result = uiModel.amountOfCurrencyTo,
            rate =  uiModel.rate,
            from = uiModel.fromCurrency,
            to = uiModel.toCurrency,
            amount = uiModel.amountOfCurrencyFrom
        )
    }


}