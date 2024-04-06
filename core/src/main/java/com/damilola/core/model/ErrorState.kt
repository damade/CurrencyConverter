package com.damilola.core.model

data class ErrorState(
    val showError: Boolean = false,
    val errorMessage: String? = null,
)

fun String.toErrorState(): ErrorState {
    return ErrorState(
        showError = true,
        errorMessage = this
    )
}