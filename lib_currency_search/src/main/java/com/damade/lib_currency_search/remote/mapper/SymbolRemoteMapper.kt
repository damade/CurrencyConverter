package com.damade.lib_currency_search.remote.mapper

import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.remote.model.SymbolRemoteModel
import com.damilola.remote.mapper.RemoteModelMapper
import javax.inject.Inject

internal class SymbolRemoteMapper @Inject constructor() :
    RemoteModelMapper<SymbolRemoteModel, SymbolEntity> {

    override fun mapFromModel(model: SymbolRemoteModel): SymbolEntity {
        return SymbolEntity(
           description = model.description,
           code =  model.code,
        )
    }

}