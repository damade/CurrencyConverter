package com.damilola.cache.room.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.damilola.cache.model.SymbolFlagCacheModel
import io.reactivex.rxjava3.core.Observable

@Dao
interface SymbolFlagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencySymbol: List<SymbolFlagCacheModel>)

    @Query("SELECT * FROM SymbolFlag")
    fun getAllCurrencySymbolsWithFlagObservable(): Observable<List<SymbolFlagCacheModel>>

    @Query("SELECT * FROM SymbolFlag")
    fun getAllCurrencySymbolsWithFlag(): List<SymbolFlagCacheModel>?

    @Query("DELETE FROM SymbolFlag")
    fun clearCurrencySymbols()

    @Transaction
    fun insertSymbolsWithFlag(carersRemoteList: List<SymbolFlagCacheModel>) {
        clearCurrencySymbols()
        insert(carersRemoteList)
    }
}
