package com.hacka.presenteperfeito.feature.signin.data.service

import com.hacka.presenteperfeito.feature.signin.data.model.LoginReqDTO
import com.hacka.presenteperfeito.feature.signin.data.model.LoginResDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("signin")
    suspend fun login(@Body loginReqDTO: LoginReqDTO): LoginResDTO
}