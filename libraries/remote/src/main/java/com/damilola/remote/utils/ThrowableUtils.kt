package com.damilola.remote.utils

import com.damilola.core.ext.errorMessage
import com.damilola.core.model.Failure
import com.damilola.core.model.NetworkConnectionLostSuddenly
import com.damilola.core.model.ResponseMessage
import com.damilola.core.model.SSLError
import com.damilola.core.model.ServiceBodyFailure
import com.damilola.core.model.TimeOut
import com.squareup.moshi.JsonAdapter
import okio.BufferedSource
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException


private const val SERVICE_BODY_ERROR_MESSAGE = "Service ERROR BODY does not match."
fun Throwable.parseException(
    adapter: JsonAdapter<ResponseMessage>,
): Failure {
    return when (this) {
        is SSLHandshakeException -> SSLError
        is SocketTimeoutException -> TimeOut
        is SSLException -> NetworkConnectionLostSuddenly
        is IOException -> Failure.UnexpectedFailure(failureMessage = this.errorMessage)
        is HttpException -> {
            val errorService = adapter.parseError(response()?.errorBody()?.source())
            if (errorService != null) {
                ServiceBodyFailure(
                    internalCode = errorService.code,
                    internalMessage = errorService.message.orEmpty(),
                )
            } else {
                Failure.UnexpectedFailure(failureMessage = SERVICE_BODY_ERROR_MESSAGE)
            }
        }

        else -> Failure.UnexpectedFailure(failureMessage = errorMessage)
    }
}

private fun JsonAdapter<ResponseMessage>.parseError(
    json: BufferedSource?,
): ResponseMessage? {
    return if (json != null) {
        fromJson(json.toString())
    } else {
        null
    }
}