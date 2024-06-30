package com.damade.lib_currency_search.remote.model.response

import com.damilola.core.model.RemoteErrorModel

internal data class CurrencySymbolResponse (
    val success: Boolean,
    val error: RemoteErrorModel?,
    val currencies: Map<String, String>?
)