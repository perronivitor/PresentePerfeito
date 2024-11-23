package com.hacka.presenteperfeito.core.auth.data.repository

import com.hacka.presenteperfeito.core.auth.data.model.RefreshTokenReqDTO
import com.hacka.presenteperfeito.core.auth.data.model.RefreshTokenResDTO
import com.hacka.presenteperfeito.core.auth.data.service.AuthService
import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherIO
import com.hacka.presenteperfeito.core.common.dispatcher.GiftPerfectDispatchers
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.LocalPreferencesRepository
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.REFRESH_TOKEN
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.TOKEN
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class AuthRepositoryImpl(
    private val preferencesRepository: LocalPreferencesRepository,
    private val authService: AuthService,
    @Named(DispatcherIO) private val giftPerfectDispatchers: GiftPerfectDispatchers
) : AuthRepository {
    override suspend fun refreshCurrentToken(): RefreshTokenResDTO =
        withContext(giftPerfectDispatchers()) {
            val refreshToken = preferencesRepository.getStringData(REFRESH_TOKEN).firstOrNull() ?: ""
            val body = RefreshTokenReqDTO(refreshToken)
            val response = authService.refreshToken(refreshTokenReqDTO = body)
            preferencesRepository.apply {
                setData(TOKEN, response.token)
                setData(REFRESH_TOKEN, response.refreshToken)
            }
            response
        }

    override suspend fun logout() = preferencesRepository.clearData()
}