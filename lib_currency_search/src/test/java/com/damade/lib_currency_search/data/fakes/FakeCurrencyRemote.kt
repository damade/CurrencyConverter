package com.damade.lib_currency_search.data.fakes

import com.damade.lib_currency_search.data.contract.remote.CurrencyRemote
import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.CurrencyHistoryEntity
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.data_generator.DummyData
import com.damilola.core.ext.toError
import com.damilola.core.ext.toSuccess
import com.damilola.core.model.Either
import com.damilola.core.model.Failure
import com.damilola.testutils.ERROR_MSG
import com.damilola.testutils.RemoteResponseType
import java.net.SocketTimeoutException

class FakeCurrencyRemote: CurrencyRemote {

   internal var remoteResponseType: RemoteResponseType = RemoteResponseType.SUCCESS

    override suspend fun fetchSymbols(): Either<Failure, List<SymbolEntity>> {
       return when(remoteResponseType){
            RemoteResponseType.SUCCESS -> listOf(DummyData.symbolEntity).toSuccess()
            else -> Failure.ThrowableFailure(SocketTimeoutException(ERROR_MSG)).toError()
        }
    }

    override suspend fun fetchConversion(
        from: String,
        to: String,
        amount: Int
    ): Either<Failure, ConversionEntity> {
        return when(remoteResponseType){
            RemoteResponseType.SUCCESS -> DummyData.conversionEntity.toSuccess()
            else -> Failure.ThrowableFailure(SocketTimeoutException(ERROR_MSG)).toError()
        }
    }

    override suspend fun fetchCurrencyHistory(date: String, symbol: String): Either<Failure, CurrencyHistoryEntity> {
        TODO("Not yet implemented")
    }
}