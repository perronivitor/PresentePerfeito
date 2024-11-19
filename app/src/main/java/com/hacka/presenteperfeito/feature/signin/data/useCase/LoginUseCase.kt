package com.hacka.presenteperfeito.feature.signin.data.useCase

import com.hacka.presenteperfeito.feature.signin.data.model.LoginReqDTO
import com.hacka.presenteperfeito.feature.signin.data.repository.SignInRepository
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class LoginUseCase(private val signInRepository: SignInRepository) {
    fun login(login: String, password: String) = flow {
        val reqBody = LoginReqDTO(login, password)
        emit(signInRepository.login(reqBody))
    }
}