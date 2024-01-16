package com.damilola.core.ext

import com.jakewharton.rxrelay3.BehaviorRelay

inline fun <T : Any> BehaviorRelay<T>.update(function: T.() -> T) {
    val previousValue = value
    checkNotNull(previousValue) {
        "BehaviourRelay (State holder) value cannot be null. Check that this relay is created " +
                "with BehaviourRelay.createDefault"
    }
    accept(function(previousValue))
}

val <T> BehaviorRelay<T>.stateValue: T
    get() = checkNotNull(value)