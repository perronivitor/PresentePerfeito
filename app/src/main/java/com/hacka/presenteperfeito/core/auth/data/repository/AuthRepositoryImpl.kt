package com.hacka.presenteperfeito.core.auth.data.repository

import com.hacka.presenteperfeito.core.auth.data.model.RefreshTokenReqDTO
import com.hacka.presenteperfeito.core.auth.data.model.RefreshTokenResDTO
import com.hacka.presenteperfeito.core.auth.data.service.AuthService
import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherIO
import com.hacka.presenteperfeito.core.common.dispatcher.GiftPerfectDispatchers
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.LocalPreferencesRepository
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.REFRESH_TOKEN
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.TOKEN
import com.hacka.presenteperfeito.core.network.interceptor.ExpiredTokenException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import retrofit2.HttpException
import java.net.HttpURLConnection

@Single
class AuthRepositoryImpl(
    private val preferencesRepository: LocalPreferencesRepository,
    private val authService: AuthService,
    @Named(DispatcherIO) private val giftPerfectDispatchers: GiftPerfectDispatchers
) : AuthRepository {
    override suspend fun refreshCurrentToken(): RefreshTokenResDTO =
        withContext(giftPerfectDispatchers()) {
            try {
                val refreshToken =
                    preferencesRepository.getStringData(REFRESH_TOKEN).firstOrNull() ?: ""
                val body = RefreshTokenReqDTO(refreshToken)
                val response = authService.refreshToken(refreshTokenReqDTO = body)
                preferencesRepository.apply {
                    setData(TOKEN, response.token)
                    setData(REFRESH_TOKEN, response.refreshToken)
                }
                response
            } catch (e: Exception) {
                if (e is HttpException && e.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    throw ExpiredTokenException()
                }
                throw e
            }
        }

    override suspend fun isAuthenticated(): Boolean = coroutineScope {
        !async {
            preferencesRepository.getStringData(TOKEN).firstOrNull()
        }.await().isNullOrBlank() && !async {
            preferencesRepository.getStringData(REFRESH_TOKEN).firstOrNull()
        }.await().isNullOrBlank()
    }

    override suspend fun logout() = preferencesRepository.clearData()
}