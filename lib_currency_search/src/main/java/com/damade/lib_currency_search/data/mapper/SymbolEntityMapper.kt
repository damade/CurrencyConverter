package com.damade.lib_currency_search.data.mapper

import com.damilola.core.mapper.EntityMapper
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.domain.model.Symbol
import javax.inject.Inject

internal class SymbolEntityMapper @Inject constructor() :
    EntityMapper<SymbolEntity, Symbol> {

    override fun mapFromEntity(entity: SymbolEntity): Symbol{
        return Symbol(
            currencyCode = entity.code,
           currencyDescription =  entity.description,
        )
    }

    override fun mapToEntity(domain: Symbol): SymbolEntity{
        return SymbolEntity(
            code = domain.currencyCode,
            description = domain.currencyDescription,
        )
    }
}