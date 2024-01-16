package com.damade.lib_currency_search.remote.mapper

import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.remote.model.response.CurrencyConversionResponse
import com.damilola.core.ext.to2dp
import com.damilola.core.ext.to4dp
import com.damilola.remote.mapper.RemoteModelMapper
import javax.inject.Inject

internal class ConversionRemoteMapper @Inject constructor() :
    RemoteModelMapper<CurrencyConversionResponse, ConversionEntity> {

    override fun mapFromModel(model: CurrencyConversionResponse): ConversionEntity {
        return ConversionEntity(
           result = model.result?.to2dp(),
           rate =  model.info.rate?.to4dp(),
            from = model.query.from,
            to = model.query.to,
            amount = model.query.amount
        )
    }

}