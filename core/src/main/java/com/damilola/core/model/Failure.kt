package com.damilola.core.model

import com.damilola.core.ext.errorMessage
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException

sealed class Failure : Throwable() {

    /**
     * Extend this class in order to provide your own
     * custom failure.
     */
    open class CustomFailure : Failure()

    data class UnexpectedFailure(
        val failureMessage: String?
    ) : Failure()

    data class ThrowableFailure(
        val throwable: Throwable
    ) : Failure()

    override val message: String?
        get() = getErrorMessage()
}

private fun Failure.getErrorMessage(): String? {
    return when (this) {
        is Failure.UnexpectedFailure -> this.failureMessage
        is Failure.ThrowableFailure -> this.throwable.errorMessage
        is Failure.CustomFailure -> this.getCustomErrorMessage()
    }
}

fun Failure.CustomFailure.getCustomErrorMessage(): String? {
    return when (this) {
        is NetworkMiddlewareFailure -> this.middleWareExceptionMessage
        is TimeOut -> SocketTimeoutException().localizedMessage
        is NetworkConnectionLostSuddenly -> SSLException("Network Lost").localizedMessage
        is ServiceBodyFailure -> this.internalMessage
        is SSLError -> SSLException("Invalid SSL Certificate").localizedMessage
        else -> "Exception not handled caused an Unknown failure"
    }
}
