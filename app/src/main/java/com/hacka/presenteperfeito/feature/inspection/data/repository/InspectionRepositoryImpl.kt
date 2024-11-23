package com.hacka.presenteperfeito.feature.inspection.data.repository

import com.hacka.presenteperfeito.core.common.dispatcher.DispatcherIO
import com.hacka.presenteperfeito.core.common.dispatcher.GiftPerfectDispatchers
import com.hacka.presenteperfeito.feature.inspection.data.model.InspectionRequestDTO
import com.hacka.presenteperfeito.feature.inspection.data.service.InspectionService
import com.hacka.presenteperfeito.feature.inspection.domain.repository.InspectionRepository
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class InspectionRepositoryImpl(
    @Named(DispatcherIO) private val giftPerfectDispatchers: GiftPerfectDispatchers,
    private val signUpService: InspectionService,
) : InspectionRepository {
    override suspend fun submit(inspectionRequestDTO: InspectionRequestDTO) =
        withContext(giftPerfectDispatchers()) {
            signUpService.submit(body = inspectionRequestDTO)
        }
}