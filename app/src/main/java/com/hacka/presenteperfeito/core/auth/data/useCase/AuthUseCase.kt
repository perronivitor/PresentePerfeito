package com.hacka.presenteperfeito.core.auth.data.useCase

import com.hacka.presenteperfeito.core.auth.data.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class AuthUseCase(private val authRepository: AuthRepository) {
    fun isAuthenticated() = flow { emit(authRepository.isAuthenticated()) }
}