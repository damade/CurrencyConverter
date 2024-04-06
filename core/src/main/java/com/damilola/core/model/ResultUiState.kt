package com.damilola.core.model

data class UiState<out T : Any?>(
    val showProgress: Boolean,
    val showEmpty: Boolean,
    val showNoNetwork: Boolean,
    val showOnRefresh: Boolean,
    val uiStateItems: List<T>,
    val uiStateItem: T?,
    val errorState: ErrorState,
) {

    companion object {
        val Hidden: UiState<Any>
            get() = UiState<Any>(
                showProgress = false,
                showEmpty = false,
                showNoNetwork = false,
                showOnRefresh = false,
                uiStateItem = null,
                uiStateItems = listOf(),
                errorState = ErrorState()
            )
    }
}