package com.damade.lib_currency_search.domain.usecase.conversion

import com.damade.lib_currency_search.domain.repository.CurrencyConversionRepository
import javax.inject.Inject

class ClearConversionWithFlagHistory @Inject constructor(
    private val currencyConversionRepository: CurrencyConversionRepository,
) {
    suspend fun execute(){
        return currencyConversionRepository.clearConvertedRateWithFlagHistory()
    }
}