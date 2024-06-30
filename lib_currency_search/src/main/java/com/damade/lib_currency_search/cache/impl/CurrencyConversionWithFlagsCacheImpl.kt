package com.damade.lib_currency_search.cache.impl

import com.damade.lib_currency_search.cache.mapper.CurrencyConversionWithFlagsCacheModelMapper
import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionWithFlagsCache
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damilola.cache.model.ConversionHistoryWithFlagsCacheModel
import com.damilola.cache.room.dao.ConversionHistoryWithFlagsDao
import javax.inject.Inject

internal class CurrencyConversionWithFlagsCacheImpl @Inject constructor(
    private val conversionHistoryDao: ConversionHistoryWithFlagsDao,
    private val currencyConversionCacheModelMapper: CurrencyConversionWithFlagsCacheModelMapper,
) : CurrencyConversionWithFlagsCache {

    override suspend fun saveCurrencyConversion(conversion: ConversionWithFlags) {
        val historyModel: ConversionHistoryWithFlagsCacheModel =
            currencyConversionCacheModelMapper.mapToModel(conversion)
        conversionHistoryDao.insert(historyModel)
    }

    override suspend fun fetchCurrencyConversionHistory(): List<ConversionWithFlags> {
        val model: List<ConversionHistoryWithFlagsCacheModel> = conversionHistoryDao.getConversionHistory()
        return currencyConversionCacheModelMapper.mapListToDomain(model = model)
    }

    override suspend fun clearCurrencyConversionHistory() {
        return conversionHistoryDao.clearHistory()
    }
}
