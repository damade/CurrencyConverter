package com.damade.lib_currency_search.domain.fakes

import com.damilola.testutils.ERROR_MSG
import com.damilola.testutils.ResponseType
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damade.lib_currency_search.domain.model.Symbol
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException
import java.net.SocketTimeoutException

internal class FakeCurrencyConversionRepository : CurrencyConversionRepository {

    private var symbolsFlow: Flow<List<Symbol>> = flowOf(listOf(DummyData.symbol))
    private var conversionFlow: Flow<Conversion> = flowOf(DummyData.conversion)
    private var conversionWithFlagsFlow: Flow<Conversion> = flowOf(DummyData.conversion)
    private var conversionHistoryFlow: Flow<List<Conversion>> = flowOf(DummyData.conversionHistory)
    private var conversionWithFlagsHistoryFlow: Flow<List<ConversionWithFlags>> =
        flowOf(DummyData.conversionWithFlagsHistory)

    private var clearConversionHistory = Unit
    private var clearConversionHistoryWithFlags = Unit

    var symbolResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            symbolsFlow = makeSymbolResponse(value)
        }

    var clearConversionHistoryResponseType: ResponseType = ResponseType.SUCCESS
        set(value) {
            field = value
            clearConversionHistory = makeClearHistoryResponse(value)
        }

    var clearConversionHistoryWithFlagsResponseType: ResponseType = ResponseType.SUCCESS
        set(value) {
            field = value
            clearConversionHistoryWithFlags = makeClearHistoryWithFlagsResponse(value)
        }

    var conversionResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            conversionFlow = makeConversionRateResponse(value)
        }

    var conversionHistoryResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            conversionHistoryFlow = makeConversionHistoryResponse(value)
        }

    var conversionWithFlagsResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            conversionWithFlagsFlow = makeConversionRateWithFlagResponse(value)
        }

    var conversionWithFlagsHistoryResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            conversionWithFlagsHistoryFlow = makeConversionHistoryWithFlagsResponse(value)
        }


    private fun makeClearHistoryResponse(type: ResponseType) {
        return when (type) {
            ResponseType.DATA, ResponseType.SUCCESS, ResponseType.EMPTY -> Unit
            ResponseType.ERROR -> throw IOException (ERROR_MSG)
        }
    }

    private fun makeClearHistoryWithFlagsResponse(type: ResponseType) {
        return when (type) {
            ResponseType.DATA, ResponseType.SUCCESS, ResponseType.EMPTY -> Unit
            ResponseType.ERROR -> throw IOException (ERROR_MSG)
        }
    }

    private fun makeSymbolResponse(type: ResponseType): Flow<List<Symbol>> {
        return when (type) {
            ResponseType.DATA, ResponseType.SUCCESS -> flowOf(listOf(DummyData.symbol))
            ResponseType.EMPTY -> flowOf()
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }

    private fun makeConversionRateResponse(type: ResponseType): Flow<Conversion> {
        return when (type) {
            ResponseType.DATA, ResponseType.SUCCESS -> flowOf(DummyData.conversion)
            ResponseType.EMPTY -> flowOf()
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }

    private fun makeConversionRateWithFlagResponse(type: ResponseType): Flow<Conversion> {
        return when (type) {
            ResponseType.DATA, ResponseType.SUCCESS -> flowOf(DummyData.conversion)
            ResponseType.EMPTY -> flowOf()
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }

    private fun makeConversionHistoryResponse(type: ResponseType): Flow<List<Conversion>> {
        return when (type) {
            ResponseType.DATA, ResponseType.SUCCESS -> flowOf(DummyData.conversionHistory)
            ResponseType.EMPTY -> flowOf(emptyList())
            ResponseType.ERROR -> flow { throw RuntimeException(ERROR_MSG) }
        }
    }

    private fun makeConversionHistoryWithFlagsResponse(type: ResponseType): Flow<List<ConversionWithFlags>> {
        return when (type) {
            ResponseType.DATA, ResponseType.SUCCESS -> flowOf(DummyData.conversionWithFlagsHistory)
            ResponseType.EMPTY -> flowOf(emptyList())
            ResponseType.ERROR -> flow { throw RuntimeException(ERROR_MSG) }
        }
    }

    override fun getCurrencySymbol(): Flow<List<Symbol>> {
        return symbolsFlow
    }

    override fun getConvertedRate(from: String, to: String, amount: Int): Flow<Conversion> {
        return conversionFlow
    }

    override fun fetchConvertedRateHistory(): Flow<List<Conversion>> {
        return conversionHistoryFlow
    }

    override suspend fun clearConvertedRateHistory() {
        return clearConversionHistory
    }

    override fun getConvertedRateWithFlags(
        from: String,
        fromCurrencyFlag: String,
        to: String,
        toCurrencyFlag: String,
        amount: Int
    ): Flow<Conversion> {
        return conversionWithFlagsFlow
    }

    override fun fetchConvertedRateWithFlagHistory(): Flow<List<ConversionWithFlags>> {
        return conversionWithFlagsHistoryFlow
    }

    override suspend fun clearConvertedRateWithFlagHistory() {
        return clearConversionHistoryWithFlags
    }
}