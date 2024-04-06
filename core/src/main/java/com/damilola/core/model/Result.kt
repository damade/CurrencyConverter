package com.damilola.core.model


sealed class Result<out T : Any> {
    data class Success<out T : Any>(val response: T?) : Result<T>()
    data class CachedSuccess<out T : Any>(val response: ResultState?) : Result<T>()
    data class NetworkError(val response: Boolean?) : Result<Nothing>()
    data class RemoteFailure<out T : Any>(val response: T?) : Result<T>()
    data class Exception(val t: Throwable) : Result<Nothing>()
}

data class ResultState(
    var failure: Result.RemoteFailure<Any>?,
    var success: Result.Success<Any>?,
) {
    var loadedFromDb: Boolean = true

}

fun ResultState.toSuc(): Any? {
    return this.success?.response
}

fun ResultState.toFail(): Failure {
    return Failure.UnexpectedFailure((this.failure?.response as String?).orEmpty())
}


