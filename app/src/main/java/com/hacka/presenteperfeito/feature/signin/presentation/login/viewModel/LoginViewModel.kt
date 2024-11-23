package com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel

import androidx.lifecycle.viewModelScope
import com.hacka.presenteperfeito.core.common.BaseViewModel
import com.hacka.presenteperfeito.core.common.validator.FormValidator
import com.hacka.presenteperfeito.feature.signin.data.useCase.LoginUseCase
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginEvents
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginUiState
import com.hacka.presenteperfeito.feature.signin.presentation.validation.SignInFormValidator.Companion.SIGN_IN_FORM
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named

@KoinViewModel
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    @Named(SIGN_IN_FORM) private val formValidator: FormValidator<LoginUiState>
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    private fun validForm() {
        setState {
            formValidator.validate(it)
        }
    }

    fun setEmail(email: String) {
        viewModelScope.launch {
            setState {
                it.copy(emailInvalidError = null, email = email)
            }
        }
    }

    fun setPassword(password: String) {
        viewModelScope.launch {

            setState {
                it.copy(passwordInvalidError = null, password = password)
            }
        }
    }

    fun doLogin() {
        validForm()
        if (currentUiState.emailInvalidError != null && currentUiState.passwordInvalidError != null) return
        viewModelScope.launch {
            loginUseCase.login(currentUiState.email ?: "", currentUiState.password ?: "")
                .catch { err ->
                    setState {
                        it.copy(
                            event = LoginEvents.LoginError(
                                message = err.message ?: ""
                            )
                        )
                    }
                }.collect {
                    setState { it.copy(event = LoginEvents.LoginSuccessfully) }
                }
        }
    }

    fun clearEvents() {
        setState {
            it.copy(event = null)
        }
    }
}