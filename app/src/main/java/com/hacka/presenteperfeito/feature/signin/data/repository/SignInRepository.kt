package com.hacka.presenteperfeito.feature.signin.data.repository

import com.hacka.presenteperfeito.feature.signin.data.model.LoginReqDTO
import com.hacka.presenteperfeito.feature.signin.data.model.LoginResDTO

interface SignInRepository {
    suspend fun login(loginReqDTO: LoginReqDTO)
}