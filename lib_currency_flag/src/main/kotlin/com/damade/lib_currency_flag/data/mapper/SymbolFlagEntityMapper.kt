package com.damade.lib_currency_flag.data.mapper

import com.damade.lib_currency_flag.data.model.SymbolFlagEntity
import com.damade.lib_currency_flag.domain.model.SymbolFlag
import com.damilola.core.mapper.EntityMapper
import javax.inject.Inject

internal class SymbolFlagEntityMapper @Inject constructor() :
    EntityMapper<SymbolFlagEntity, SymbolFlag> {

    override fun mapFromEntity(entity: SymbolFlagEntity): SymbolFlag {
        return with(entity) {
            SymbolFlag(
                currencyCode = code,
                currencyFlag = flag,
                currencyFlagUtf = flagUtf
            )
        }
    }

    override fun mapToEntity(domain: SymbolFlag): SymbolFlagEntity {
        return with(domain) {
            SymbolFlagEntity(
                code = currencyCode,
                flag = currencyFlag,
                flagUtf = currencyFlagUtf
            )
        }
    }
}