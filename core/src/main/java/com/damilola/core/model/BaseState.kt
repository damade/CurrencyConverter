package com.damilola.core.model

sealed class PresentationState<out T : Any?> {
    object Hidden : PresentationState<Any>()
    object Loading : PresentationState<Any>()
    object Empty : PresentationState<Any>()
    object NoNetwork : PresentationState<Any>()
    object Refresh : PresentationState<Any>()
    data class Error(val errorState: ErrorState) : PresentationState<Any>()
    data class LoadedItems<out T : Any>(val items: List<T>) : PresentationState<T>()
    data class LoadedItem<out T : Any>(val uiStateItem: T?) : PresentationState<T?>()
}