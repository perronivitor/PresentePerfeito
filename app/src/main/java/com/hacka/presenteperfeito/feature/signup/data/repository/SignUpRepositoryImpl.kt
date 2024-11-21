package com.hacka.presenteperfeito.feature.signup.data.repository

import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherIO
import com.hacka.presenteperfeito.core.common.dispatcher.GiftPerfectDispatchers
import com.hacka.presenteperfeito.feature.signup.data.model.SignUpReqDTO
import com.hacka.presenteperfeito.feature.signup.data.model.SignUpResDTO
import com.hacka.presenteperfeito.feature.signup.data.service.SignUpService
import com.hacka.presenteperfeito.feature.signup.domain.repository.SignUpRepository
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class SignUpRepositoryImpl(
    @Named(DispatcherIO) private val giftPerfectDispatchers: GiftPerfectDispatchers,
    private val signUpService: SignUpService,
) : SignUpRepository {
    override suspend fun submit(signUpReqDTO: SignUpReqDTO): SignUpResDTO =
        withContext(giftPerfectDispatchers()) {
            signUpService.submit(body = signUpReqDTO)
        }
}