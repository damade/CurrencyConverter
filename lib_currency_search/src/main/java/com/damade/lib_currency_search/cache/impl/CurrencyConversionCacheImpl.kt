package com.damade.lib_currency_search.cache.impl

import com.damade.lib_currency_search.cache.mapper.CurrencyConversionCacheModelMapper
import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionCache
import com.damade.lib_currency_search.domain.model.Conversion
import com.damilola.cache.model.CurrencyConversionHistoryCacheModel
import com.damilola.cache.room.dao.CurrencyConversionHistoryDao
import javax.inject.Inject

internal class CurrencyConversionCacheImpl @Inject constructor(
    private val conversionHistoryDao: CurrencyConversionHistoryDao,
    private val currencyConversionCacheModelMapper: CurrencyConversionCacheModelMapper,
) : CurrencyConversionCache {
    override suspend fun saveCurrencyConversion(conversion: Conversion) {
        val historyModel: CurrencyConversionHistoryCacheModel =
            currencyConversionCacheModelMapper.mapToModel(conversion)
        conversionHistoryDao.insert(historyModel)
    }

    override suspend fun fetchCurrencyConversionHistory(): List<Conversion> {
        val model: List<CurrencyConversionHistoryCacheModel> = conversionHistoryDao.getConversionHistory()
        return currencyConversionCacheModelMapper.mapListToDomain(model = model)
    }

    override suspend fun clearCurrencyConversionHistory() {
        return conversionHistoryDao.clearHistory()
    }
}
