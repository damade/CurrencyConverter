package com.damilola.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "Symbol")
data class SymbolCacheModel(
    @PrimaryKey
    @ColumnInfo(name = "currencyCode") var currencyCode: String,
    @ColumnInfo(name = "currencyDescription") var currencyDescription: String,
)
