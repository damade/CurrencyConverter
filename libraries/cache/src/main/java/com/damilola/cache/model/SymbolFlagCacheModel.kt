package com.damilola.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SymbolFlag")
data class SymbolFlagCacheModel(
    @PrimaryKey
    @ColumnInfo(name = "currencyCode") val currencyCode: String,
    @ColumnInfo(name = "currencyFlags") val currencyFlag: String,
    @ColumnInfo(name = "currencyFlagsUtf") val currencyFlagUtf: String
)
