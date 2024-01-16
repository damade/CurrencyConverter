package com.damade.lib_currency_search.cache.impl

import com.damade.lib_currency_search.cache.mapper.CurrencyConversionCacheModelMapper
import com.damade.lib_currency_search.data.contract.cache.CurrencyConversionCache
import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damilola.cache.model.CurrencyConversionHistoryCacheModel
import com.damilola.cache.room.dao.CurrencyConversionHistoryDao
import javax.inject.Inject

internal class CurrencyConversionCacheImpl @Inject constructor(
    private val conversionHistoryDao: CurrencyConversionHistoryDao,
    private val currencyConversionCacheModelMapper: CurrencyConversionCacheModelMapper
) : CurrencyConversionCache {
    override suspend fun saveCurrencyConversion(conversionEntity: ConversionEntity) {
        val historyModel : CurrencyConversionHistoryCacheModel =
            currencyConversionCacheModelMapper.mapToModel(conversionEntity)
        conversionHistoryDao.insert(historyModel)
    }

    override suspend fun fetchCurrencyConversionHistory(): List<ConversionEntity> {
        val model: List<CurrencyConversionHistoryCacheModel> = conversionHistoryDao.getConversionHistory()
        return currencyConversionCacheModelMapper.mapListToEntity(model = model)
    }

    override suspend fun clearCurrencyConversionHistory() {
        return conversionHistoryDao.clearHistory()
    }
}
