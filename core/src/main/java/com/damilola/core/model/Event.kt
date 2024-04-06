package com.damilola.core.model

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    var hasNotBeenHandled = true
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (!hasNotBeenHandled) {
            hasNotBeenHandled = true
            content
        } else {
            null
        }
    }

    /**
     * A util function that allows an action to be taken if the content has not been consumed.
     */
    fun consume(action: (T) -> Unit) {
        if (!hasNotBeenHandled) {
            hasNotBeenHandled = true
            action(content)
        }
    }

    /**
     * An async (Coroutines) util function that allows an action to be taken if the content has not been consumed.
     */
    suspend fun consumeAsync(action: suspend (T) -> Unit) {
        if (!hasNotBeenHandled) {
            hasNotBeenHandled = true
            action(content)
        }
    }


    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}