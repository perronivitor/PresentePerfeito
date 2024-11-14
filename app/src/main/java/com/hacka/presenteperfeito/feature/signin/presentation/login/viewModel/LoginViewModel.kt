package com.hacka.presenteperfeito.feature.signin.presentation.login.viewModel

import androidx.lifecycle.viewModelScope
import com.hacka.presenteperfeito.core.common.BaseViewModel
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginEvents
import com.hacka.presenteperfeito.feature.signin.presentation.login.uiState.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//ONCREATE

//PAUSE
//RESUME

//ONDESTROY
class LoginViewModel : BaseViewModel<LoginUiState>(LoginUiState()) {

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
        //Servidor
        viewModelScope.launch(Dispatchers.IO) {
            setState {
                it.copy(event = LoginEvents.LoginError("E-mail ou senha incorreta"))
            }
        }
    }

    fun clearEvents() {
        setState {
            it.copy(event = null)
        }
    }
}