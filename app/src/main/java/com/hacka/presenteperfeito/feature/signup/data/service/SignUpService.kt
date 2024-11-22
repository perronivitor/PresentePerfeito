package com.hacka.presenteperfeito.feature.signup.data.service

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpRequest
import com.hacka.presenteperfeito.feature.signup.data.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("signup")
    suspend fun submit(@Body body: SignUpRequest): SignUpResponse
}