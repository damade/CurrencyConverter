package com.damade.lib_currency_search.remote.model.response

import com.damade.lib_currency_search.remote.model.SymbolRemoteModel

internal data class CurrencySymbolResponse (
    val motd: MotdResponse,

    val success: Boolean,

    val symbols: Map<String, SymbolRemoteModel>
)