package com.hacka.presenteperfeito.feature.signup.presentation.validation

import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.validator.EmailValidator
import com.hacka.presenteperfeito.core.common.validator.FormValidator
import com.hacka.presenteperfeito.core.common.validator.PasswordValidator
import com.hacka.presenteperfeito.feature.signup.presentation.uiState.SignUpFormState
import com.hacka.presenteperfeito.feature.signup.presentation.validation.SignUpFormValidator.Companion.SIGN_UP_FORM
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
@Named(SIGN_UP_FORM)
class SignUpFormValidator : FormValidator<SignUpFormState> {

    override fun validate(formState: SignUpFormState): SignUpFormState {
        val isFirstNameValid = formState.firstName.isNotEmpty()
        val isLastNameValid = formState.lastName.isNotEmpty()
        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)
        val isPasswordConfirmationValid = PasswordValidator.isValid(formState.passwordConfirmation)
                && formState.passwordConfirmation == formState.password

        val hasError = listOf(
            isFirstNameValid,
            isLastNameValid,
            isEmailValid,
            isPasswordValid,
            isPasswordConfirmationValid,
        ).any { !it }

        return formState.copy(
            firstNameError = if (!isFirstNameValid) R.string.error_message_field_blank else null,
            lastNameError = if (!isLastNameValid) R.string.error_message_field_blank else null,
            emailError = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordError = if (!isPasswordValid) R.string.error_message_password_invalid else null,
            passwordConfirmationError = if (!isPasswordConfirmationValid) R.string.error_message_password_confirmation_invalid else null,
            hasError = hasError,
        )

    }
    companion object {
        const val SIGN_UP_FORM = "SIGN_UP_FORM"
    }
}