package com.hacka.presenteperfeito.feature.signin.data.repository

import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherIO
import com.hacka.presenteperfeito.core.common.dispatcher.GiftPerfectDispatchers
import com.hacka.presenteperfeito.feature.signin.data.model.LoginReqDTO
import com.hacka.presenteperfeito.feature.signin.data.model.LoginResDTO
import com.hacka.presenteperfeito.feature.signin.data.service.SignInService
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class SignInRepositoryImpl(
    @Named(DispatcherIO) private val giftPerfectDispatchers: GiftPerfectDispatchers,
    private val signInService: SignInService
) : SignInRepository {
    override suspend fun login(loginReqDTO: LoginReqDTO): LoginResDTO =
        withContext(giftPerfectDispatchers()) {
            signInService.login(loginReqDTO)
        }
}