package com.hacka.presenteperfeito.feature.signin.data.repository

import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherIO
import com.hacka.presenteperfeito.core.common.dispatcher.GiftPerfectDispatchers
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.LocalPreferencesRepository
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.REFRESH_TOKEN
import com.hacka.presenteperfeito.core.common.localData.dataStore.repository.TOKEN
import com.hacka.presenteperfeito.feature.signin.data.model.LoginReqDTO
import com.hacka.presenteperfeito.feature.signin.data.service.SignInService
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class SignInRepositoryImpl(
    private val localPreferencesRepository: LocalPreferencesRepository,
    @Named(DispatcherIO) private val giftPerfectDispatchers: GiftPerfectDispatchers,
    private val signInService: SignInService
) : SignInRepository {
    override suspend fun login(loginReqDTO: LoginReqDTO) = withContext(giftPerfectDispatchers()) {
        val response = signInService.login(loginReqDTO)
        localPreferencesRepository.apply {
            setData(TOKEN, response.token)
            setData(REFRESH_TOKEN, response.refreshToken)
        }
        Unit
    }
}