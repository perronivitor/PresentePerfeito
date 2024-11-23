package com.hacka.presenteperfeito.feature.signup.presentation.uiState

sealed interface SignUpFormEvent {
    data object Submit : SignUpFormEvent
    data class SubmitError(val message: String) : SignUpFormEvent
}