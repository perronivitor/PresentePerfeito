package com.hacka.presenteperfeito.feature.inspection.domain.usecase

import com.hacka.presenteperfeito.feature.inspection.data.model.InspectionRequestDTO
import com.hacka.presenteperfeito.feature.inspection.domain.repository.InspectionRepository
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class InspectionUseCase(private val inspectionRepository: InspectionRepository) {
    fun submit(inspectionRequestDTO: InspectionRequestDTO) = flow {
        emit(inspectionRepository.submit(inspectionRequestDTO))
    }
}