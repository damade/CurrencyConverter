package com.damade.lib_currency_search.domain.usecase.conversion

import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import com.damilola.core.usecase.BaseUseCase
import com.damilola.core_android.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConversionWithFlagHistory @Inject constructor(
    private val currencyConversionRepository: CurrencyConversionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BaseUseCase<Unit, List<ConversionWithFlags>>() {

    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: Unit?): Flow<List<ConversionWithFlags>> {
        return currencyConversionRepository.fetchConvertedRateWithFlagHistory()
    }
}