package com.damilola.core.model

data class RemoteErrorModel(val code: Int, val info: String)

fun RemoteErrorModel.toFailure(): Failure = ServiceBodyFailure(
    internalCode = code,
    internalMessage = info,
)
