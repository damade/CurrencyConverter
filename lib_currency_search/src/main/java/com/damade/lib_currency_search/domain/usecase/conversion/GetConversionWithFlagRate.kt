package com.damade.lib_currency_search.domain.usecase.conversion


import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionWithFlagsQuery
import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import com.damilola.core.exception.requireParams
import com.damilola.core.usecase.BaseUseCase
import com.damilola.core_android.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConversionWithFlagRate @Inject constructor(
    private val currencyConversionRepository: CurrencyConversionRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BaseUseCase<ConversionWithFlagsQuery, Conversion>() {

    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: ConversionWithFlagsQuery?): Flow<Conversion> {
        requireParams(params)
        return currencyConversionRepository.getConvertedRateWithFlags(
            params.from, params.fromCurrencyFlag,
            params.to, params.toCurrencyFlag, params.amount)
    }
}