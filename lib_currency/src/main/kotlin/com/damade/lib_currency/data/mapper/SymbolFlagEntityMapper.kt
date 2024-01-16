package com.damade.lib_currency.data.mapper

import com.damade.lib_currency.data.model.SymbolFlagEntity
import com.damade.lib_currency.domain.model.SymbolFlag
import com.damilola.core.mapper.EntityMapper
import javax.inject.Inject

internal class SymbolFlagEntityMapper @Inject constructor() :
    EntityMapper<SymbolFlagEntity, SymbolFlag> {

    override fun mapFromEntity(entity: SymbolFlagEntity): SymbolFlag {
        return with(entity) {
            SymbolFlag(
                currencyCode = code,
                currencyFlag = flag,
                currencyFlagUtF = flagUtf
            )
        }
    }

    override fun mapToEntity(domain: SymbolFlag): SymbolFlagEntity {
        return with(domain) {
            SymbolFlagEntity(
                code = currencyCode,
                flag = currencyFlag,
                flagUtf = currencyFlagUtF
            )
        }
    }
}