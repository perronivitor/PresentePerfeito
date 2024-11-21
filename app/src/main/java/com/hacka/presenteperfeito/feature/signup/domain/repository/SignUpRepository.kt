package com.hacka.presenteperfeito.feature.signup.domain.repository

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpReqDTO
import com.hacka.presenteperfeito.feature.signup.data.model.SignUpResDTO

interface SignUpRepository {
    suspend fun submit(signUpReqDTO: SignUpReqDTO): SignUpResDTO
}