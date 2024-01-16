package com.damilola.ft_currency.model

import androidx.compose.runtime.saveable.mapSaver

data class SymbolFlagUi(
    val currencyCode: String,
    val currencyFlag: String,
    val currencyFlagUtF: String
)

val SymbolFlagSaver = run {
    val currencyCodeKey = "currencyCode"
    val currencyFlagKey = "currencyFlag"
    val currencyFlagUtFKey = "currencyFlagUtF"
    mapSaver(
        save = { mapOf(
            currencyCodeKey to it.currencyCode,
            currencyFlagKey to it.currencyFlag,
            currencyFlagUtFKey to it.currencyFlagUtF
        ) },
        restore = {
            SymbolFlagUi(
                currencyCode = it[currencyCodeKey] as String,
                currencyFlag =  it[currencyFlagKey] as String,
                currencyFlagUtF = it[currencyFlagUtFKey] as String
            ) }
    )
}