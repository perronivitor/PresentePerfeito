package com.hacka.presenteperfeito.feature.signup.domain.usecase

import com.hacka.presenteperfeito.feature.signup.domain.repository.SignUpRepository
import com.hacka.presenteperfeito.feature.signup.domain.model.CreateUser
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class SignUpUseCase(private val signUpRepository: SignUpRepository) {
    fun submit(user: CreateUser) = flow {
        val body = user.toSignUpRequest()
        emit(signUpRepository.submit(body))
    }
}