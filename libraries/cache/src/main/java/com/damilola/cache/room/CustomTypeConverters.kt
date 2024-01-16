package com.damilola.cache.room

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*
import javax.inject.Inject

internal class CustomTypeConverters {

    private val moshi: Moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, String::class.java)
    private val listAdapter = moshi.adapter<List<String>>(listType)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun listToJsonString(value: List<String>?): String =  listAdapter.toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = listAdapter.fromJson(value).orEmpty()

}