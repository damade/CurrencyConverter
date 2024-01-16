package com.damade.lib_currency_search.remote.mapper

import com.damade.lib_currency_search.data.model.CurrencyHistoryEntity
import com.damade.lib_currency_search.remote.model.response.CurrencyHistoryResponse
import com.damilola.core.ext.to4dp
import com.damilola.remote.mapper.RemoteModelMapper
import javax.inject.Inject

internal class CurrencyHistoryRemoteMapper @Inject constructor() :
    RemoteModelMapper<CurrencyHistoryResponse, CurrencyHistoryEntity> {

    override fun mapFromModel(model: CurrencyHistoryResponse): CurrencyHistoryEntity {
        return CurrencyHistoryEntity(
           base = model.base,
           rate =  model.rates.values.first().to4dp(),
           currency = model.rates.keys.first(),
           historical = model.historical,
            date = model.date
        )
    }

}