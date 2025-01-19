package {{packageName}}.model

sealed interface {{className}}NavigationEvent {
    data object Default : {{className}}NavigationEvent
    data object NavigateBack : {{className}}NavigationEvent
}