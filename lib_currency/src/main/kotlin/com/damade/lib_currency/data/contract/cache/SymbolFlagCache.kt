package com.damade.lib_currency.data.contract.cache

import com.damade.lib_currency.data.model.SymbolFlagEntity
import io.reactivex.rxjava3.core.Observable

internal interface SymbolFlagCache {
    fun saveCurrencySymbolFlag(symbolFlagEntities: List<SymbolFlagEntity>)

    fun fetchCurrencySymbolFlagWithObservable(): Observable<List<SymbolFlagEntity>>

    fun fetchCurrencySymbolFlag(): List<SymbolFlagEntity>?


}
