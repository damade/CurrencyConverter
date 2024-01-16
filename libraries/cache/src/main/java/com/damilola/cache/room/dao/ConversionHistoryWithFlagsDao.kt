package com.damilola.cache.room.dao

import androidx.room.*
import com.damilola.cache.model.ConversionHistoryWithFlagsCacheModel
import com.damilola.cache.model.CurrencyConversionHistoryCacheModel

@Dao
interface ConversionHistoryWithFlagsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cchCacheModel: ConversionHistoryWithFlagsCacheModel)

    @Query("SELECT * FROM ConversionHistoryWithFlags ORDER BY dateAdded DESC")
    suspend fun getConversionHistory(): List<ConversionHistoryWithFlagsCacheModel>

    @Query("DELETE FROM ConversionHistoryWithFlags")
    suspend fun clearHistory()

}