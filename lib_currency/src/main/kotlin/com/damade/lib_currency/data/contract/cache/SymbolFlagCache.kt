package com.damade.lib_currency.data.contract.cache

import com.damade.lib_currency.domain.model.SymbolFlag
import io.reactivex.rxjava3.core.Observable

internal interface SymbolFlagCache {
    fun saveCurrencySymbolFlag(symbolFlag: List<SymbolFlag>)
    fun fetchCurrencySymbolFlagWithObservable(): Observable<List<SymbolFlag>>
    fun fetchCurrencySymbolFlag(): List<SymbolFlag>?


}
