package com.hacka.presenteperfeito.feature.signup.data.usecase

import com.hacka.presenteperfeito.feature.signup.data.repository.SignUpRepository
import com.hacka.presenteperfeito.feature.signup.presentation.model.CreateUser
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class SignUpUseCase(private val signUpRepository: SignUpRepository) {
    fun submit(user: CreateUser) = flow {
        val body = user.toSignUpReqDTO()
        emit(signUpRepository.submit(body))
    }
}