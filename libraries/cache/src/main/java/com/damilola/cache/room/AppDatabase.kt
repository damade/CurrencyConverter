package com.damilola.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.damilola.cache.model.ConversionHistoryWithFlagsCacheModel
import com.damilola.cache.model.CurrencyConversionHistoryCacheModel
import com.damilola.cache.model.RemoteKeys
import com.damilola.cache.model.SymbolCacheModel
import com.damilola.cache.model.SymbolFlagCacheModel
import com.damilola.cache.room.dao.ConversionHistoryWithFlagsDao
import com.damilola.cache.room.dao.CurrencyConversionHistoryDao
import com.damilola.cache.room.dao.RemoteKeysDao
import com.damilola.cache.room.dao.SymbolDao
import com.damilola.cache.room.dao.SymbolFlagDao

@Database(
    entities = [
        SymbolCacheModel::class, SymbolFlagCacheModel::class,
        CurrencyConversionHistoryCacheModel::class, ConversionHistoryWithFlagsCacheModel::class,
        RemoteKeys::class
    ],
    version = AppDatabase.DATABASE_VERSION,
    exportSchema = true,
)
@TypeConverters(CustomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val symbolDao: SymbolDao
    abstract val symbolFlagDao: SymbolFlagDao
    abstract val currencyConversionHistoryDao: CurrencyConversionHistoryDao
    abstract val conversionHistoryWithFlagsDao: ConversionHistoryWithFlagsDao
    abstract val remoteKeysDao: RemoteKeysDao

    companion object {
        const val DATABASE_VERSION = 1

        fun build(context: Context, dataBaseName: String): AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            dataBaseName
        ).fallbackToDestructiveMigration().build()
    }
}