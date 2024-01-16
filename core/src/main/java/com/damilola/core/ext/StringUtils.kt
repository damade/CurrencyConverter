package com.damilola.core.ext

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun String.camelCase(): String {
    val delimiter = " "
    val separator = " "
    return this.split(delimiter).joinToString(separator = separator) {
        it.lowercase().replaceFirstChar { char -> char.titlecase() }
    }
}

@OptIn(ExperimentalContracts::class)
fun String?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return !this.isNullOrEmpty()
}

@OptIn(ExperimentalContracts::class)
fun String?.isNotNullOrBlank(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrBlank != null)
    }
    return !this.isNullOrBlank()
}

fun String?.isNotEqual(secondString: String): Boolean {
    return !this.equals(secondString, true)
}

fun String?.isNotContainedIn(stringArray: Array<String>): Boolean {
    var isItEqual = false
    for (eachString in stringArray) {
        isItEqual = this.equals(eachString, true)
    }
    return !isItEqual
}


fun String?.isContainedIn(stringArray: Array<String>): Boolean {
    var isItEqual = false
    for (eachString in stringArray) {
        isItEqual = this.equals(eachString, true)
    }
    return isItEqual
}