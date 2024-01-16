package com.damade.lib_currency.data.repository

import com.damade.lib_currency.data.contract.cache.SymbolFlagCache
import com.damade.lib_currency.data.contract.remote.SymbolFlagsRemote
import com.damade.lib_currency.data.mapper.SymbolFlagEntityMapper
import com.damade.lib_currency.data.model.SymbolFlagEntity
import com.damade.lib_currency.domain.model.SymbolFlag
import com.damilola.core.model.Either
import com.damilola.core.model.Failure
import com.damilola.core_android.di.RxIoScheduler
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

internal class CurrencyConversionRepositoryImpl @Inject constructor(
    private val symbolFlagsRemote: SymbolFlagsRemote,
    private val symbolFlagCache: SymbolFlagCache,
    @RxIoScheduler private val ioScheduler: Scheduler,
    private val symbolFlagEntityMapper: SymbolFlagEntityMapper
) : CurrencyConversionRepository {

    override fun getCurrencySymbolWithFlag(): Observable<Either<Failure, List<SymbolFlag>>> {

        return symbolFlagsRemote.fetchCurrencyWithFlags()
            .subscribeOn(ioScheduler)
            .onErrorResumeNext { fetchCurrencySymbolWithFlagLocally() }
            .map {
                symbolFlagCache.saveCurrencySymbolFlag(it)
                Either.Success(symbolFlagEntityMapper.mapFromEntityList(it))
            }

    }

    private fun fetchCurrencySymbolWithFlagLocally(): Observable<List<SymbolFlagEntity>> {
        return symbolFlagCache
            .fetchCurrencySymbolFlagWithObservable()
    }
}

