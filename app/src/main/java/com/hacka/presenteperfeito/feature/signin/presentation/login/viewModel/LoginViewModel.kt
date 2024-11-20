package com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel

import androidx.lifecycle.viewModelScope
import com.hacka.presenteperfeito.core.common.BaseViewModel
import com.hacka.presenteperfeito.feature.signin.data.useCase.LoginUseCase
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginEvents
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(private val loginUseCase: LoginUseCase) :
    BaseViewModel<LoginUiState>(LoginUiState()) {

    fun setEmail(email: String) {
        viewModelScope.launch {
            setState {
                it.copy(email = email)
            }
        }
    }

    fun setPassword(password: String) {
        viewModelScope.launch {
            setState {
                it.copy(password = password)
            }
        }
    }

    fun doLogin() {
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