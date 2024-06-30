package com.damilola.core.ext

import java.util.regex.Pattern
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
    return (this != null && this.isBlankOptimized())
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

/**
 * @see <a href="https://www.romainguy.dev/posts/2024/speeding-up-isblank/">Romain Guy's Article</a>
 */
fun CharSequence.isBlankOptimized(): Boolean {
    for (i in 0 until length - 1) {
        val c = this[i]
        if (!Character.isWhitespace(c) &&
            c != '\u00a0' && c != '\u2007' && c != '\u202f'
        ) {
            return false
        }
    }
    return true
}

fun String?.isContainedIn(stringArray: Array<String>): Boolean {
    var isItEqual = false
    for (eachString in stringArray) {
        isItEqual = this.equals(eachString, true)
    }
    return isItEqual
}

val String.youtubeLink: String?
    get() = extractYTId(this)

private fun extractYTId(ytUrl: String): String? {
    var vId: String? = null

    val pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"

    val compiledPattern = Pattern.compile(pattern)
    val matcher = compiledPattern.matcher(ytUrl)
    if (matcher.find()) {
        vId = matcher.group()
    }

    return vId
}

val String.gender: String
    get() = genderParser(this)

private fun genderParser(title: String): String {
    if (title.contentEquals("Mr", true)) {
        return "Male"
    } else if (title.contentEquals("Mrs", true)) {
        return "Female"
    } else if (title.contentEquals("Miss", true)) {
        return "Female"
    }
    return "Never Mind"
}