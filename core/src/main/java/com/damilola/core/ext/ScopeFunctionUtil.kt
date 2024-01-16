package com.damilola.core.ext

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun <T> T.fluentIf(condition: Boolean, block: T.() -> T): T {
    return if (condition) block() else this
}

inline fun <T> T.ifThenDo(condition: Boolean, block: (T) -> Unit) {
    if (condition) block(this)
}

@OptIn(ExperimentalContracts::class)
inline fun <T> T.ifThenDo(predicate: (T) -> Boolean, doBlock: (T) -> Unit) {
    contract {
        callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
    }
    if (predicate(this)) doBlock(this)
}

inline fun <T : Any, R> T?.whenNotNull(callback: (T) -> R): R? {
    return this?.let(callback)
}