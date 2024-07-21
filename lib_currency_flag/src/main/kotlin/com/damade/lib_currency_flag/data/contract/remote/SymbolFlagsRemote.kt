package com.damade.lib_currency_flag.data.contract.remote

import com.damade.lib_currency_flag.data.model.SymbolFlagEntity
import io.reactivex.rxjava3.core.Observable


internal interface SymbolFlagsRemote {

   fun fetchCurrencyWithFlags(): Observable<List<SymbolFlagEntity>>

}