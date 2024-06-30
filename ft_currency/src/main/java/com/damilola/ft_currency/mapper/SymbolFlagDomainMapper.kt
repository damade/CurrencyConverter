package com.damilola.ft_currency.mapper


import com.damade.lib_currency.domain.model.SymbolFlag
import com.damilola.core.mapper.DomainMapper
import com.damilola.ft_currency.model.SymbolFlagUi
import javax.inject.Inject

internal class SymbolFlagDomainMapper @Inject constructor() :
    DomainMapper<SymbolFlag, SymbolFlagUi> {

    override fun mapFromDomain(domain: SymbolFlag): SymbolFlagUi {
        return SymbolFlagUi(
            currencyCode = domain.currencyCode,
            currencyFlag = domain.currencyFlag,
            currencyFlagUtF = domain.currencyFlagUtf
        )
    }

    override fun mapToDomain(uiModel: SymbolFlagUi): SymbolFlag {
        return SymbolFlag(
            currencyCode = uiModel.currencyCode,
            currencyFlag = uiModel.currencyFlag,
            currencyFlagUtf = uiModel.currencyFlagUtF
        )
    }
}