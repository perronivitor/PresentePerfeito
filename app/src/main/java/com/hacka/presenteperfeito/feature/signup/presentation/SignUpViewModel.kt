package com.hacka.presenteperfeito.feature.signup.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.BaseViewModel
import com.hacka.presenteperfeito.core.common.validator.FormValidator
import com.hacka.presenteperfeito.feature.signup.data.usecase.SignUpUseCase
import com.hacka.presenteperfeito.feature.signup.presentation.model.CreateUser
import com.hacka.presenteperfeito.feature.signup.presentation.uiState.SignUpFormState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpViewModel(
    private val formValidator: FormValidator<SignUpFormState>,
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel<SignUpFormState>(SignUpFormState()) {

    fun setProfilePictureUri(uri: Uri) {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(profilePictureUri = uri)
            }
        }
    }

    fun setFirstName(firstName: String) {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(firstName = firstName, firstNameError = null)
            }
        }
    }

    fun setLastName(lastName: String) {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(lastName = lastName, lastNameError = null)
            }
        }
    }

    fun setEmail(email: String) {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(email = email)
            }
        }
    }

    fun setPassword(password: String) {
        viewModelScope.launch {
            setState { formState ->
                updatePasswordExtraText()
                formState.copy(password = password)
            }
        }
    }

    fun setPasswordConfirmation(passwordConfirmation: String) {
        viewModelScope.launch {
            setState { formState ->
                updatePasswordExtraText()
                formState.copy(passwordConfirmation = passwordConfirmation)
            }
        }
    }

    private fun updatePasswordExtraText() {
        viewModelScope.launch {
            setState { formState ->
                val isPasswordMatch =
                    formState.password.isNotBlank() && formState.password == formState.passwordConfirmation

                formState.copy(
                    passwordExtraTextId = if (isPasswordMatch) {
                        R.string.feature_sign_up_passwords_match
                    } else {
                        null
                    }
                )
            }
        }
    }

    fun setOpenProfilePictureModalBottomSheet() {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(isProfilePictureModalBottomSheetOpen = true)
            }
        }
    }

    fun setCloseProfilePictureModalBottomSheet() {
        viewModelScope.launch {
            setState { formState ->
                formState.copy(isProfilePictureModalBottomSheetOpen = false)
            }
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(uiState.value).also { newFormState ->
            setState {
                it.copy(
                    firstNameError = newFormState.firstNameError,
                    lastNameError = newFormState.lastNameError,
                    emailError = newFormState.emailError,
                    passwordError = newFormState.passwordError,
                    passwordConfirmationError = newFormState.passwordConfirmationError,
                )
            }
        }.hasError
    }

    fun doSubmit() {
        if (isValidForm()) {
            viewModelScope.launch {
                val user = CreateUser(
                    email = currentUiState.email,
                    name = currentUiState.firstName,
                    lastName = currentUiState.lastName,
                    password = currentUiState.password
                )
                signUpUseCase.submit(user).catch { err ->
                    err
                }.collect {
                    it
                }

            }
        }
    }

    fun clearEvents() {
        setState {
            it.copy(event = null)
        }
    }
}
