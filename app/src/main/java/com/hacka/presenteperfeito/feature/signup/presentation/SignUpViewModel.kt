package com.hacka.presenteperfeito.feature.signup.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.validator.FormValidator
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val formValidator: FormValidator<SignUpFormState>,
) : ViewModel() {

    var formState by mutableStateOf(SignUpFormState())
        private set

    fun onFormEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ProfilePhotoUriChanged -> {
                formState = formState.copy(profilePictureUri = event.uri)
            }

            is SignUpFormEvent.FirstNameChanged -> {
                formState = formState.copy(firstName = event.firstName, firstNameError = null)
            }

            is SignUpFormEvent.LastNameChanged -> {
                formState = formState.copy(lastName = event.lastName, lastNameError = null)
            }

            is SignUpFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email, emailError = null)
            }

            is SignUpFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password, passwordError = null)
                updatePasswordExtraText()
            }

            is SignUpFormEvent.PasswordConfirmationChanged -> {
                formState = formState.copy(passwordConfirmation = event.passwordConfirmation)
                updatePasswordExtraText()
            }

            SignUpFormEvent.OpenProfilePictureModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = true)
            }

            SignUpFormEvent.CloseProfilePictureModalBottomSheet -> {
                formState = formState.copy(isProfilePictureModalBottomSheetOpen = false)
            }

            is SignUpFormEvent.Submit -> {
                doSignUp()
            }

        }
    }

    private fun updatePasswordExtraText() {
        val isPasswordMatch =
            formState.password.isNotBlank() && formState.password == formState.passwordConfirmation

        formState = formState.copy(
            passwordExtraTextId = if (isPasswordMatch) {
                R.string.feature_sign_up_passwords_match
            } else {
                null
            }
        )
    }

    private fun doSignUp() {
        if (isValidForm()) {
            formState = formState.copy(isLoading = true)
            viewModelScope.launch {
                //TODO: chamar endpoint de cadastro
            }
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(formState).also {
            formState = it
        }.hasError
    }
}
