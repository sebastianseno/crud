package com.sebastianseno.crudapps.common

sealed class UiState {
    object Refreshing : UiState()
    object Loading : UiState()
    object Success : UiState()
    class Error(val message: String) : UiState()
}