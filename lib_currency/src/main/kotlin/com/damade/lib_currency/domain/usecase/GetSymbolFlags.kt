package com.damade.lib_currency.domain.usecase

import com.damade.lib_currency.data.repository.CurrencyConversionRepository
import com.damade.lib_currency.domain.model.SymbolFlag
import com.damilola.core.model.Either
import com.damilola.core.model.Failure
import com.damilola.core.usecase.BaseUseCaseForRx
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetSymbolFlags @Inject constructor(
    private val currencyConversionRepository: CurrencyConversionRepository,
) : BaseUseCaseForRx <Any, List<SymbolFlag>>() {


    override fun onExecute(parameter: Any?): Observable<Either<Failure, List<SymbolFlag>>> {
       return currencyConversionRepository.getCurrencySymbolWithFlag()
    }
}