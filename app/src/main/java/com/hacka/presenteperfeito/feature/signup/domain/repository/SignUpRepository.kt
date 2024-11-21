package com.hacka.presenteperfeito.feature.signup.domain.repository

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpRequest
import com.hacka.presenteperfeito.feature.signup.data.model.SignUpResponse

interface SignUpRepository {
    suspend fun submit(signUpRequest: SignUpRequest): SignUpResponse
}