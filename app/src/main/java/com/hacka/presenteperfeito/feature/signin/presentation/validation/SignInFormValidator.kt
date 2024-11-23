package com.hacka.presenteperfeito.feature.signin.presentation.validation

import com.hacka.presenteperfeito.R
import com.hacka.presenteperfeito.core.common.validator.EmailValidator
import com.hacka.presenteperfeito.core.common.validator.FormValidator
import com.hacka.presenteperfeito.core.common.validator.PasswordValidator
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginUiState
import com.hacka.presenteperfeito.feature.signin.presentation.validation.SignInFormValidator.Companion.SIGN_IN_FORM
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
@Named(SIGN_IN_FORM)
class SignInFormValidator : FormValidator<LoginUiState> {
    override fun validate(formState: LoginUiState): LoginUiState {
        val isEmailValid = EmailValidator.isValid(formState.email ?: "")
        val isPasswordValid = PasswordValidator.isValid(formState.password ?: "")

        return formState.copy(
            emailInvalidError = if (!isEmailValid) R.string.error_message_email_invalid else null,
            passwordInvalidError = if (!isPasswordValid) R.string.error_message_password_invalid else null
        )
    }

    companion object {
        const val SIGN_IN_FORM = "SIGN_IN_FORM"
    }
}