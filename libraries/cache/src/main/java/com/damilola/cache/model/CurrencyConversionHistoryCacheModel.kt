package com.damilola.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "CurrencyConversionHistory")
data class CurrencyConversionHistoryCacheModel(
                @ColumnInfo(name = "from") val from: String,
                @ColumnInfo(name = "to") val to: String,
                @ColumnInfo(name = "amount") val amount: Int,
                @ColumnInfo(name = "rate") val rate: Double?,
                @ColumnInfo(name = "result") val result: Double?,
                @ColumnInfo(name = "dateAdded") val dateAdded : Date = Date()
){
    @PrimaryKey(autoGenerate = true) var id: Int? = null

}
