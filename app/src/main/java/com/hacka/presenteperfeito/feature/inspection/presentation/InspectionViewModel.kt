package com.hacka.presenteperfeito.feature.inspection.presentation

import androidx.lifecycle.viewModelScope
import com.hacka.presenteperfeito.core.common.BaseViewModel
import com.hacka.presenteperfeito.core.common.validator.FormValidator
import com.hacka.presenteperfeito.feature.inspection.data.model.InspectionRequestDTO
import com.hacka.presenteperfeito.feature.inspection.domain.usecase.InspectionUseCase
import com.hacka.presenteperfeito.feature.inspection.presentation.uiState.InspectionFormEvent.Submit
import com.hacka.presenteperfeito.feature.inspection.presentation.uiState.InspectionFormEvent.SubmitError
import com.hacka.presenteperfeito.feature.inspection.presentation.uiState.InspectionUiState
import com.hacka.presenteperfeito.feature.inspection.presentation.validation.InspectionFormValidator.Companion.INSPECTION_FORM_VALIDATOR
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named

@KoinViewModel
class InspectionViewModel(
    @Named(INSPECTION_FORM_VALIDATOR) private val formValidator: FormValidator<InspectionUiState>,
    private val inspectionUseCase: InspectionUseCase,
) : BaseViewModel<InspectionUiState>(InspectionUiState()) {

    fun setName(name: String) {
        setState {
            it.copy(name = name)
        }
    }

    fun setRelationship(relationship: String) {
        setState {
            it.copy(relationship = relationship)
        }
    }

    fun setPhoneNumber(phoneNumber: String) {
        setState {
            it.copy(phoneNumber = phoneNumber)
        }
    }

    fun setOccasion(occasion: String) {
        setState {
            it.copy(occasion = occasion)
        }
    }

    fun clearEvents() {
        setState {
            it.copy(event = null)
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(uiState.value).also { newFormState ->
            setState {
                it.copy(
                    nameError = newFormState.nameError,
                    relationshipError = newFormState.relationshipError,
                    phoneNumberError = newFormState.phoneNumberError,
                    occasionError = newFormState.occasionError
                )
            }
        }.hasError
    }

    fun doCreateInspection() {
        if (!isValidForm()) {
            viewModelScope.launch {
                val createInspection = InspectionRequestDTO(
                    name = currentUiState.name,
                    relationShip = currentUiState.relationship,
                    whatsappPhoneNumber = currentUiState.phoneNumber,
                    occasion = currentUiState.occasion
                )
                inspectionUseCase.submit(inspectionRequestDTO = createInspection).catch { error ->
                    onError(message = error.message.orEmpty())
                }.collect {
                    openHome()
                }
            }
        }

    }

    private fun onError(message: String) {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(event = SubmitError(message))
            }
        }
    }

    private fun openHome() {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(event = Submit)
            }
        }
    }
}