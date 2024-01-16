package com.damade.lib_currency.remote.impl


import com.damade.lib_currency.data.contract.remote.SymbolFlagsRemote
import com.damade.lib_currency.data.model.SymbolFlagEntity
import com.damade.lib_currency.remote.mapper.SymbolFlagRemoteMapper
import com.damilola.remote.graphqlremotes.libCurrency.SymbolFlagsRemoteController
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

internal class SymbolFlagsRemoteImpl @Inject constructor(
    private val symbolFlagRemoteMapper: SymbolFlagRemoteMapper,
    private val symbolFlagsRemoteController: SymbolFlagsRemoteController
) : SymbolFlagsRemote {

    override fun fetchCurrencyWithFlags(): Observable<List<SymbolFlagEntity>> {
        return symbolFlagsRemoteController.fetchCurrencyWithFlags()
            .map {
                symbolFlagRemoteMapper.mapModelList(it)
            }
    }

}
