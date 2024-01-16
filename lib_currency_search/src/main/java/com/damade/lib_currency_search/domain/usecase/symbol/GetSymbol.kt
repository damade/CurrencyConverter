package com.damade.lib_currency_search.domain.usecase.symbol

import com.damade.lib_currency_search.domain.model.Symbol
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import com.damilola.core.usecase.BaseUseCase
import com.damilola.core_android.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSymbol @Inject constructor(
    private val currencyConversionRepository: CurrencyConversionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BaseUseCase<Any, List<Symbol>>() {

    override val dispatcher: CoroutineDispatcher
       get() = ioDispatcher

    override fun execute(params: Any?): Flow<List<Symbol>> {
        return currencyConversionRepository.getCurrencySymbol()
    }
}