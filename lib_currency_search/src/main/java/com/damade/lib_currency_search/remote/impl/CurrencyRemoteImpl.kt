package com.damade.lib_currency_search.remote.impl

import com.damade.lib_currency_search.data.contract.remote.CurrencyRemote
import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.CurrencyHistoryEntity
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.remote.ApiService
import com.damade.lib_currency_search.remote.mapper.ConversionRemoteMapper
import com.damade.lib_currency_search.remote.mapper.CurrencyHistoryRemoteMapper
import com.damade.lib_currency_search.remote.mapper.SymbolRemoteMapper
import com.damilola.core.middleware.MiddlewaresProducer
import com.damilola.core.model.Either
import com.damilola.core.model.Failure
import com.damilola.core.model.ResponseMessage
import com.damilola.core.model.toFailure
import com.damilola.core_android.di.IoDispatcher
import com.damilola.remote.ext.call
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class CurrencyRemoteImpl @Inject constructor(
    private val middlewareProvider: MiddlewaresProducer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val errorAdapter: JsonAdapter<ResponseMessage>,
    private val mainService: ApiService,
    private val symbolRemoteMapper: SymbolRemoteMapper,
    private val conversionRemoteMapper: ConversionRemoteMapper,
    private val currencyHistoryRemoteMapper: CurrencyHistoryRemoteMapper,
) : CurrencyRemote {

    override suspend fun fetchSymbols(): Either<Failure, List<SymbolEntity>?> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                mainService.getCurrencySymbol()
            }
        ).let { response ->
            response.mapSuccessAndError(
                errorTransformation = { it.error?.toFailure() },
                shouldMapToFailure = { it.success.not() },
            ) { successResponse ->
                successResponse.currencies?.entries.orEmpty()
            }.coMapSuccess { symbolRemoteMapper.mapModelList(it.toList()) }
        }
    }

    override suspend fun fetchConversion(
        from: String,
        to: String,
        amount: Int,
    ): Either<Failure, ConversionEntity?> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                mainService.getConvertedExchange(from, to, amount)
            }
        ).let { response ->
            response.mapSuccess { responseItems -> responseItems }.coMapSuccess {
                conversionRemoteMapper.mapFromModel(it)
            }
        }
    }

    override suspend fun fetchCurrencyHistory(
        date: String,
        symbol: String,
    ): Either<Failure, CurrencyHistoryEntity?> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                mainService.getCurrencySymbolHistory(date, symbol)
            }
        ).let { response ->
            response.mapSuccess { responseItems -> responseItems }.coMapSuccess {
                currencyHistoryRemoteMapper.mapFromModel(it)
            }
        }
    }

}