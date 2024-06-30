package com.damade.lib_currency_search.remote.mapper

import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damilola.remote.mapper.RemoteModelMapper
import javax.inject.Inject

internal class SymbolRemoteMapper @Inject constructor() :
    RemoteModelMapper<Map.Entry<String, String>, SymbolEntity> {

    override fun mapFromModel(model: Map.Entry<String, String>): SymbolEntity {
        return SymbolEntity(
            description = model.value,
            code = model.key,
        )
    }

}