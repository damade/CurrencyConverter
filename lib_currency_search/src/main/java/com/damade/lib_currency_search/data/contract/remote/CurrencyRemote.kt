package com.damade.lib_currency_search.data.contract.remote

import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.CurrencyHistoryEntity
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damilola.core.model.Failure
import com.damilola.core.model.Either


internal interface CurrencyRemote {

    suspend fun fetchSymbols(): Either<Failure, List<SymbolEntity>?>

    suspend fun fetchConversion(from: String,
                            to: String,
                            amount: Int): Either<Failure, ConversionEntity?>

    suspend fun fetchCurrencyHistory(date: String,
                                symbol: String): Either<Failure, CurrencyHistoryEntity?>

}