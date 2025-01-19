package {{packageName}}.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import {{packageName}}.model.{{className}}NavigationEvent
import {{packageName}}.model.{{className}}UiSideEffects
import {{packageName}}.model.{{className}}UiState

@HiltViewModel
class {{className}}ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow({{className}}UiState.Initial)
    val uiState: StateFlow<{{className}}UiState> = _uiState

    private val _uiSideEffects = MutableStateFlow({{className}}UiSideEffects.Default)
    val uiSideEffects: StateFlow<{{className}}UiSideEffects> = _uiSideEffects

    private val _navigationEvent = MutableStateFlow({{className}}NavigationEvent.Default)
    val navigationEvent: StateFlow<{{className}}NavigationEvent> = _navigationEvent
}