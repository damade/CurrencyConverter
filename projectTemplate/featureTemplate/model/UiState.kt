package {{packageName}}.model

sealed class {{className}}UiState {
    data object Initial : {{className}}UiState()
    data object Loading : {{className}}UiState()
}