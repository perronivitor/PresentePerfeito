package com.hacka.presenteperfeito.core.auth.data.repository

import com.hacka.presenteperfeito.core.auth.data.model.RefreshTokenResDTO

interface AuthRepository {
    suspend fun refreshCurrentToken(): RefreshTokenResDTO
    suspend fun isAuthenticated(): Boolean
    suspend fun logout()
}