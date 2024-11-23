package com.hacka.presenteperfeito.core.auth.data.service

import com.hacka.presenteperfeito.core.auth.data.model.RefreshTokenReqDTO
import com.hacka.presenteperfeito.core.auth.data.model.RefreshTokenResDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("signin/refresh-token")
    suspend fun refreshToken(@Body refreshTokenReqDTO: RefreshTokenReqDTO): RefreshTokenResDTO
}