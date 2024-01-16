package com.damade.lib_currency.remote.mapper

import com.damade.lib_currency.data.model.SymbolFlagEntity
import com.damilola.remote.graphqlremotes.libCurrency.model.SymbolFlagResponse
import com.damilola.remote.mapper.RemoteModelMapper
import javax.inject.Inject

internal class SymbolFlagRemoteMapper @Inject constructor() :
    RemoteModelMapper<SymbolFlagResponse, SymbolFlagEntity> {

    override fun mapFromModel(model: SymbolFlagResponse): SymbolFlagEntity {
        return with(model) {
            SymbolFlagEntity(
                code = remoteCurrencyCode,
                flag = remoteCurrencyFlags,
                flagUtf = remoteCurrencyFlagsUtF
            )
        }
    }

}

