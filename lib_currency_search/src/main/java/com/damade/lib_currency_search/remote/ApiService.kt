package com.damade.lib_currency_search.remote

import com.damade.lib_currency_search.remote.model.response.CurrencyConversionResponse
import com.damade.lib_currency_search.remote.model.response.CurrencyHistoryResponse
import com.damade.lib_currency_search.remote.model.response.CurrencySymbolResponse
import retrofit2.Response
import retrofit2.http.*

internal interface ApiService {

    @GET("/symbols")
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

//    @GET("/live")
//    suspend fun getConvertedCurrency
//                (@Query("currencies") currenciesYouWant: String,
//                 @Query("format") format: Int = 1): Response<CurrencyLiveConversionResponse>
//
//    @GET("/api/v7/convert")
//    suspend fun getConvertedExchange(@Query("q") q: String,
//                                     @Query("compact") compact: String = "ultra"): retrofit2.Response<Map<String,Double>>
//

}