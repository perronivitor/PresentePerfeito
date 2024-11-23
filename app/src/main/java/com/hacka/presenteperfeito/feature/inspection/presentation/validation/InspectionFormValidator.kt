package com.hacka.presenteperfeito.feature.inspection.presentation.validation

import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.validator.FormValidator
import com.hacka.presenteperfeito.core.common.validator.PhoneNumberValidator
import com.hacka.presenteperfeito.feature.inspection.presentation.uiState.InspectionUiState
import com.hacka.presenteperfeito.feature.inspection.presentation.validation.InspectionFormValidator.Companion.INSPECTION_FORM_VALIDATOR
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
@Named(INSPECTION_FORM_VALIDATOR)
class InspectionFormValidator : FormValidator<InspectionUiState> {

    override fun validate(formState: InspectionUiState): InspectionUiState {
        val isNameValid = formState.name.isNotEmpty()
        val isOccasionValid = formState.occasion.isNotEmpty()
        val isRelationshipValid = formState.relationship.isNotEmpty()
        val isPhoneNumberValid = PhoneNumberValidator.isValid(formState.phoneNumber)


        val hasError = listOf(
            isNameValid,
            isOccasionValid,
            isRelationshipValid,
            isPhoneNumberValid,
        ).any { !it }

        return formState.copy(
            nameError = if (!isNameValid) R.string.error_message_field_blank else null,
            occasionError = if (!isOccasionValid) R.string.error_message_field_blank else null,
            relationshipError = if (!isRelationshipValid) R.string.error_message_field_blank else null,
            phoneNumberError = if (!isPhoneNumberValid) R.string.error_phone_number_invalid else null,
            hasError = hasError,
        )
    }

    companion object {
        const val INSPECTION_FORM_VALIDATOR = "INSPECTION_FORM_VALIDATOR"
    }
}