package com.damade.lib_currency_flag.data.contract.cache

import com.damade.lib_currency_flag.domain.model.SymbolFlag
import io.reactivex.rxjava3.core.Observable

internal interface SymbolFlagCache {
    fun saveCurrencySymbolFlag(symbolFlag: List<SymbolFlag>)
    fun fetchCurrencySymbolFlagWithObservable(): Observable<List<SymbolFlag>>
    fun fetchCurrencySymbolFlag(): List<SymbolFlag>?


}
