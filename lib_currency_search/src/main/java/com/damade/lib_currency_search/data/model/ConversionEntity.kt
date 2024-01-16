package com.damade.lib_currency_search.data.model

data class ConversionEntity(
    val from: String,
    val to: String,
    val amount: Int,
    val result: Double?,
    val rate: Double?
)
