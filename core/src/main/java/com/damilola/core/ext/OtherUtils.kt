package com.damilola.core.ext

fun commaSeparator(amount: Any?): String {
    return if (amount != null && amount is Int) {
        "%,.3d".format(amount)
    } else if (amount != null && (amount is Float || amount is Double)) {
        "%,.3f".format(amount)
    } else {
        "0.0"
    }
}

