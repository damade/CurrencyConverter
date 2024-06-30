package com.damade.lib_currency_search.remote

import com.damade.lib_currency_search.remote.model.response.CurrencyConversionResponse
import com.damade.lib_currency_search.remote.model.response.CurrencyHistoryResponse
import com.damade.lib_currency_search.remote.model.response.CurrencySymbolResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ApiService {

    @GET("/list")
    suspend fun getCurrencySymbol(): CurrencySymbolResponse

    @GET("/convert")
    suspend fun getConvertedExchange(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Int,
    ): CurrencyConversionResponse

    @GET("/{date}")
    suspend fun getCurrencySymbolHistory(
        @Path("date") date: String,
        @Query("symbols") symbol: String,
    ): CurrencyHistoryResponse
}