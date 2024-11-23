package com.hacka.presenteperfeito.feature.signup.domain.repository

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpRequestDTO
import com.hacka.presenteperfeito.feature.signup.data.model.SignUpResponseDTO

interface SignUpRepository {
    suspend fun submit(signUpRequestDTO: SignUpRequestDTO): SignUpResponseDTO
}