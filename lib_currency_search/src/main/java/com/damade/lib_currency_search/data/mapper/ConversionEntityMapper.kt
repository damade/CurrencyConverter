package com.damade.lib_currency_search.data.mapper

import com.damilola.core.mapper.EntityMapper
import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.domain.model.Conversion
import javax.inject.Inject

internal class ConversionEntityMapper @Inject constructor() :
    EntityMapper<ConversionEntity, Conversion> {

    override fun mapFromEntity(entity: ConversionEntity): Conversion{
        return Conversion(
            result = entity.result,
            rate =  entity.rate,
            from = entity.from,
            to = entity.to,
            amount = entity.amount
        )
    }

    override fun mapToEntity(domain: Conversion): ConversionEntity {
        return ConversionEntity(
            result = domain.result,
            rate =  domain.rate,
            from = domain.from,
            to = domain.to,
            amount = domain.amount
        )
    }
}