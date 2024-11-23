package com.hacka.presenteperfeito.feature.inspection.presentation.uiState

sealed interface InspectionFormEvent {
    data object Submit : InspectionFormEvent
    data class SubmitError(val message: String) : InspectionFormEvent
}