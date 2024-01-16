package com.damilola.ft_home.mapper

import com.damade.lib_currency_search.domain.model.Symbol
import com.damilola.core.mapper.DomainMapper
import com.damilola.ft_home.model.SymbolUi
import javax.inject.Inject

internal class SymbolDomainMapper @Inject constructor() :
    DomainMapper<Symbol, SymbolUi> {

    override fun mapFromDomain(domain: Symbol): SymbolUi {
        return SymbolUi(
            currencyCode = domain.currencyCode,
            currencyDescription = domain.currencyDescription,
        )
    }

    override fun mapToDomain(uiModel: SymbolUi): Symbol {
        return Symbol(
            currencyCode = uiModel.currencyCode,
            currencyDescription = uiModel.currencyDescription,
        )
    }
}