package com.hacka.presenteperfeito.feature.signin.presentation.login.uiState

import com.hacka.presenteperfeito.core.common.UiState

data class LoginUiState(
    val email: String? = null, val password: String? = null, val event: LoginEvents? = null
): UiState

sealed interface LoginEvents {
    data class LoginError(val message: String) : LoginEvents
    data object LoginSuccessfully : LoginEvents
}