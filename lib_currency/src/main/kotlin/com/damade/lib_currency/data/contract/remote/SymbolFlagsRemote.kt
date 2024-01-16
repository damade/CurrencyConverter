package com.damade.lib_currency.data.contract.remote

import com.damade.lib_currency.data.model.SymbolFlagEntity
import com.damilola.core.model.Failure
import com.damilola.core.model.Either
import io.reactivex.rxjava3.core.Observable


internal interface SymbolFlagsRemote {

   fun fetchCurrencyWithFlags(): Observable<List<SymbolFlagEntity>>

}