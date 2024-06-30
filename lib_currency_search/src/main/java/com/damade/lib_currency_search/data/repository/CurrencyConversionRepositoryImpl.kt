package com.damade.lib_currency_search.data.repository

import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionCache
import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionWithFlagsCache
import com.damade.lib_currency_search.data.contract.cache.CurrencySymbolCache
import com.damade.lib_currency_search.data.contract.remote.CurrencyRemote
import com.damade.lib_currency_search.data.mapper.ConversionEntityMapper
import com.damade.lib_currency_search.data.mapper.ConversionWithFlagsEntityMapper
import com.damade.lib_currency_search.data.mapper.SymbolEntityMapper
import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damade.lib_currency_search.domain.model.Symbol
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.core.model.Either
import com.damilola.core.model.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CurrencyConversionRepositoryImpl @Inject constructor(
    private val currencyRemote: CurrencyRemote,
    private val currencyConversionCache: CurrencyConversionCache,
    private val currencyConversionWithFlagsCache: CurrencyConversionWithFlagsCache,
    private val currencySymbolCache: CurrencySymbolCache,
    private val conversionEntityMapper: ConversionEntityMapper,
    private val conversionWithFlagsEntityMapper: ConversionWithFlagsEntityMapper,
    private val symbolEntityMapper: SymbolEntityMapper,
) : CurrencyConversionRepository {

    override fun getCurrencySymbol(): Flow<List<Symbol>> {
        return flow {
            val symbolRemote: Either<Failure, List<SymbolEntity>?> = currencyRemote.fetchSymbols()

            if (symbolRemote.isSuccess) {
                val symbolEntity: List<SymbolEntity>? = symbolRemote.getSuccessOrNull()

                symbolEntity?.let {
                    val symbols = symbolEntityMapper.mapFromEntityList(entities = it)
                    emit(symbols)
                    currencySymbolCache.saveCurrencySymbol(symbol = symbols)
                }
            } else if (symbolRemote.isError) {
                val cachedSymbol: List<Symbol>? = currencySymbolCache.fetchCurrencySymbol()
                if (cachedSymbol != null && cachedSymbol.isNotNullOrEmpty()) {
                    emit(cachedSymbol)
                } else {
                    val failureEntity: Failure? = symbolRemote.getFailureOrNull()
                    if (failureEntity != null) {
                        throw failureEntity
                    }
                }

            }
        }
    }

    override fun getConvertedRate(
        from: String,
        to: String,
        amount: Int,
    ): Flow<Conversion> {
        return flow {
            val conversionRemote: Either<Failure, ConversionEntity?> =
                currencyRemote.fetchConversion(from, to, amount)

            if (conversionRemote.isSuccess) {
                val conversionEntity: ConversionEntity? = conversionRemote.getSuccessOrNull()
                conversionEntity?.let {
                    val conversion = conversionEntityMapper.mapFromEntity(it)
                    currencyConversionCache.saveCurrencyConversion(conversion = conversion)
                    emit(conversion)
                }
            } else if (conversionRemote.isError) {
                val failureEntity: Failure? = conversionRemote.getFailureOrNull()
                if (failureEntity != null) {
                    throw failureEntity
                }
            }
        }
    }

    override fun fetchConvertedRateHistory(): Flow<List<Conversion>> {
        return flow {
            emit(currencyConversionCache.fetchCurrencyConversionHistory())
        }
    }

    override suspend fun clearConvertedRateHistory() {
        currencyConversionCache.clearCurrencyConversionHistory()
    }

    override fun getConvertedRateWithFlags(
        from: String,
        fromCurrencyFlag: String,
        to: String,
        toCurrencyFlag: String,
        amount: Int,
    ): Flow<Conversion> {
        return flow {
            val conversionRemote: Either<Failure, ConversionEntity?> =
                currencyRemote.fetchConversion(from, to, amount)

            if (conversionRemote.isSuccess) {
                val conversionEntity: ConversionEntity? = conversionRemote.getSuccessOrNull()
                conversionEntity?.let {
                    val conversion = conversionEntityMapper.mapFromEntity(it)
                    val conversionWithFlags = conversionWithFlagsEntityMapper.mapFromConversionEntity(
                        entity = it,
                        fromCurrencyFlag = fromCurrencyFlag,
                        toCurrencyFlag = toCurrencyFlag,
                    )

                    currencyConversionWithFlagsCache.saveCurrencyConversion(conversionWithFlags)
                    emit(conversion)
                }
            } else if (conversionRemote.isError) {
                val failureEntity: Failure? = conversionRemote.getFailureOrNull()
                if (failureEntity != null) {
                    throw failureEntity
                }
            }
        }
    }

    override fun fetchConvertedRateWithFlagHistory(): Flow<List<ConversionWithFlags>> {
        return flow {
            emit(currencyConversionWithFlagsCache.fetchCurrencyConversionHistory())
        }
    }

    override suspend fun clearConvertedRateWithFlagHistory() {
        currencyConversionWithFlagsCache.clearCurrencyConversionHistory()
    }
}