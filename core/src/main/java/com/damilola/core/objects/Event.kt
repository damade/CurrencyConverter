package com.damilola.core.objects

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
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}