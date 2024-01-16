package com.damilola.ft_home.model

import com.damilola.core.model.ErrorState

sealed interface HomeUiState {
    object Hidden : HomeUiState
    object Loading : HomeUiState
    object SymbolsEmpty : HomeUiState
    object NoNetwork : HomeUiState
    object Refresh : HomeUiState
    object Reset : HomeUiState
    data class Error(val errorState: ErrorState) : HomeUiState
    data class SymbolsLoaded(val symbols: List<SymbolUi>) : HomeUiState
    data class Conversion(val conversion: ConversionUi) : HomeUiState
}