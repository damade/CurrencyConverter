package com.damilola.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.damilola.cache.model.SymbolCacheModel

@Dao
interface SymbolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencySymbol: List<SymbolCacheModel>)

    @Query("SELECT * FROM Symbol")
    fun getAllCurrencySymbols(): List<SymbolCacheModel>

    @Query("DELETE FROM Symbol")
    suspend fun clearCurrencySymbols()

    @Transaction
    suspend fun insertSymbols(carersRemoteList: List<SymbolCacheModel>){
        clearCurrencySymbols()
        insert(carersRemoteList)
    }
}