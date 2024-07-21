package com.damade.lib_currency_flag.data.repository

import com.damade.lib_currency_flag.domain.model.SymbolFlag
import com.damilola.core.model.Either
import com.damilola.core.model.Failure
import io.reactivex.rxjava3.core.Observable


interface CurrencyConversionRepository {

    fun getCurrencySymbolWithFlag(): Observable<Either<Failure, List<SymbolFlag>>>
}