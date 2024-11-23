package com.hacka.presenteperfeito.feature.signup.data.service

import com.hacka.presenteperfeito.feature.signup.data.model.SignUpRequestDTO
import com.hacka.presenteperfeito.feature.signup.data.model.SignUpResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("signup")
    suspend fun submit(@Body body: SignUpRequestDTO): SignUpResponseDTO
}