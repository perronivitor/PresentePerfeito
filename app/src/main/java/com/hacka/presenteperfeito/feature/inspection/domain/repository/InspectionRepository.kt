package com.hacka.presenteperfeito.feature.inspection.domain.repository

import com.hacka.presenteperfeito.feature.inspection.data.model.InspectionRequestDTO

interface InspectionRepository {
    suspend fun submit(inspectionRequestDTO: InspectionRequestDTO)
}