package com.damilola.cache.room.dao

import androidx.room.*
import com.damilola.cache.model.CurrencyConversionHistoryCacheModel

@Dao
interface CurrencyConversionHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cchCacheModel: CurrencyConversionHistoryCacheModel)

    @Query("SELECT * FROM CurrencyConversionHistory ORDER BY dateAdded DESC")
    suspend fun getConversionHistory(): List<CurrencyConversionHistoryCacheModel>

    @Query("DELETE FROM CurrencyConversionHistory")
    suspend fun clearHistory()

}