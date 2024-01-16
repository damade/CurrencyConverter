package com.damilola.remote.graphqlremotes.libCurrency


import com.damilola.remote.graphqlremotes.libCurrency.model.SymbolFlagResponse
import io.reactivex.rxjava3.core.Observable


interface SymbolFlagsRemoteController {

   fun fetchCurrencyWithFlags(): Observable<List<SymbolFlagResponse>>

}