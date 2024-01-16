package com.damade.lib_currency_search.data.repository

import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionCache
import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionWithFlagsCache
import com.damade.lib_currency_search.data.contract.cache.CurrencySymbolCache
import com.damade.lib_currency_search.data.contract.remote.CurrencyRemote
import com.damade.lib_currency_search.data.mapper.ConversionEntityMapper
import com.damade.lib_currency_search.data.mapper.ConversionWithFlagsEntityMapper
import com.damade.lib_currency_search.data.mapper.SymbolEntityMapper
import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damade.lib_currency_search.domain.model.Symbol
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.core.model.Failure
import com.damilola.core.model.Either
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
    private val symbolEntityMapper: SymbolEntityMapper
) : CurrencyConversionRepository {

    override fun getCurrencySymbol(): Flow<List<Symbol>> {
        return flow {
            val symbolRemote: Either<Failure, List<SymbolEntity>?> = currencyRemote.fetchSymbols()

            if (symbolRemote.isSuccess) {
                val symbolEntity: List<SymbolEntity>? = symbolRemote.getSuccessOrNull()
                symbolEntity?.let {
                    emit(symbolEntityMapper.mapFromEntityList(it))
                    currencySymbolCache.saveCurrencySymbol(it)
                }
            } else if (symbolRemote.isError) {
                val cachedSymbol: List<SymbolEntity>? = currencySymbolCache.fetchCurrencySymbol()
                if (cachedSymbol.isNotNullOrEmpty() && cachedSymbol != null) {
                    emit(symbolEntityMapper.mapFromEntityList(cachedSymbol))
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
        amount: Int
    ): Flow<Conversion> {
        return flow {
            val conversionRemote: Either<Failure, ConversionEntity?> =
                currencyRemote.fetchConversion(from, to, amount)

            if (conversionRemote.isSuccess) {
                val conversionEntity: ConversionEntity? = conversionRemote.getSuccessOrNull()
                conversionEntity?.let {
                    emit(conversionEntityMapper.mapFromEntity(it))
                    currencyConversionCache.saveCurrencyConversion(it)
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
            val cachedConversion: List<ConversionEntity> =
                currencyConversionCache.fetchCurrencyConversionHistory()

            emit(conversionEntityMapper.mapFromEntityList(cachedConversion))
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
        amount: Int
    ): Flow<Conversion> {
        return flow {
            val conversionRemote: Either<Failure, ConversionEntity?> =
                currencyRemote.fetchConversion(from, to, amount)

            if (conversionRemote.isSuccess) {
                val conversionEntity: ConversionEntity? = conversionRemote.getSuccessOrNull()
                conversionEntity?.let {
                    emit(conversionEntityMapper.mapFromEntity(it))
                    val conversionWithFlagsEntity = ConversionWithFlagsEntity(
                        from = conversionEntity.from,
                        fromCurrencyFlag = fromCurrencyFlag,
                        to = conversionEntity.to,
                        toCurrencyFlag = toCurrencyFlag,
                        amount = conversionEntity.amount,
                        result = conversionEntity.result,
                        rate = conversionEntity.rate
                    )

                    currencyConversionWithFlagsCache.saveCurrencyConversion(
                        conversionWithFlagsEntity
                    )
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
            val cachedConversion: List<ConversionWithFlagsEntity> =
                currencyConversionWithFlagsCache.fetchCurrencyConversionHistory()

            emit(conversionWithFlagsEntityMapper.mapFromEntityList(cachedConversion))
        }
    }

    override suspend fun clearConvertedRateWithFlagHistory() {
        currencyConversionWithFlagsCache.clearCurrencyConversionHistory()
    }
}