package com.damilola.core.model

import com.damilola.core.ext.errorMessage
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException


/**
 * This is our custom class for all possible exceptions in the app.
 */
sealed class Failure : Throwable() {

    /**
     * Extend this class in order to provide your own
     * custom failure.
     */
    open class CustomFailure : Failure()

    data class UnexpectedFailure(
        val failureMessage: String,
    ) : Failure()

    data class ThrowableFailure(
        val throwable: Throwable,
    ) : Failure()

    override val message: String?
        get() = getErrorMessage()
}

class NetworkMiddlewareFailure(
    val middleWareExceptionMessage: String,
) : Failure.CustomFailure()

object TimeOut : Failure.CustomFailure() {
    private fun readResolve(): Any = TimeOut
}

object EitherFailure : Failure.CustomFailure() {
    private fun readResolve(): Any = EitherFailure
}

object NetworkConnectionLostSuddenly : Failure.CustomFailure() {
    private fun readResolve(): Any = NetworkConnectionLostSuddenly
}

object SSLError : Failure.CustomFailure() {
    private fun readResolve(): Any = SSLError
}

/**
 * If your service return some custom error use this with the given errors you expect.
 */
data class ServiceBodyFailure(
    val internalCode: Int,
    val internalMessage: String,
) : Failure.CustomFailure()

private fun Failure.getErrorMessage(): String {
    return when (this) {
        is Failure.UnexpectedFailure -> this.failureMessage
        is Failure.ThrowableFailure -> this.throwable.errorMessage
        is Failure.CustomFailure -> this.getCustomErrorMessage()
    }
}

fun Failure.CustomFailure.getCustomErrorMessage(): String {
    return when (this) {
        is NetworkMiddlewareFailure -> this.middleWareExceptionMessage
        is TimeOut -> SocketTimeoutException().localizedMessage
        is NetworkConnectionLostSuddenly -> SSLException("Network Lost").localizedMessage
        is ServiceBodyFailure -> this.internalMessage
        is SSLError -> SSLException("Invalid SSL Certificate").localizedMessage
        is EitherFailure -> "Could not parse the response from Either class"
        else -> "Exception not handled caused an Unknown failure"
    }
}
