package {{packageName}}.model

sealed interface {{className}}UiSideEffects {
    data object Default : {{className}}UiSideEffects
    data object ShowSuccessToast : {{className}}UiSideEffects
}