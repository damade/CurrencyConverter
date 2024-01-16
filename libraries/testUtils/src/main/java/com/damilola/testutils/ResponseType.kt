package com.damilola.testutils

enum class ResponseType {
    DATA,
    EMPTY,
    ERROR,
    SUCCESS,
}

enum class RemoteResponseType {
    SUCCESS,
    FAILURE,
}

const val ERROR_MSG: String = "Error_Msg"