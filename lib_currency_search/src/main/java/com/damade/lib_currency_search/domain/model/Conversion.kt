package com.damade.lib_currency_search.domain.model

data class Conversion(
    val from: String,
    val to: String,
    val amount: Int,
    val result: Double?,
    val rate: Double?,
)
