package com.damilola.core.ext

import java.util.regex.Pattern

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

fun commaSeparator(amount: Any?): String {
    return if (amount != null && amount is Int) {
        "%,.3d".format(amount)
    } else if (amount != null && (amount is Float || amount is Double)) {
        "%,.3f".format(amount)
    } else {
        " "
    }
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
