package com.damade.lib_currency_search.domain.repository

import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damade.lib_currency_search.domain.model.Symbol
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionRepository {

    fun getCurrencySymbol(): Flow<List<Symbol>>

    fun getConvertedRate(
        from: String,
        to: String,
        amount: Int
    ): Flow<Conversion>

    fun fetchConvertedRateHistory(): Flow<List<Conversion>>

    suspend fun clearConvertedRateHistory()

    fun getConvertedRateWithFlags(
        from: String,
        fromCurrencyFlag: String,
        to: String,
        toCurrencyFlag: String,
        amount: Int
    ): Flow<Conversion>

    fun fetchConvertedRateWithFlagHistory(): Flow<List<ConversionWithFlags>>

    suspend fun clearConvertedRateWithFlagHistory()
}