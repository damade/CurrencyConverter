package com.damade.lib_currency_search.domain.usecase.conversion


import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionQuery
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import com.damilola.core.exception.requireParams
import com.damilola.core.usecase.BaseUseCase
import com.damilola.core_android.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConversionRate @Inject constructor(
    private val currencyConversionRepository: CurrencyConversionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BaseUseCase<ConversionQuery, Conversion>() {

    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: ConversionQuery?): Flow<Conversion> {
        requireParams(params)
        return currencyConversionRepository.getConvertedRate(params.from, params.to, params.amount)
    }
}