package com.hacka.presenteperfeito.feature.inspection.data.service

import com.hacka.presenteperfeito.feature.inspection.data.model.InspectionRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface InspectionService {
    @POST("investigation/create-investigation")
    suspend fun submit(@Body body: InspectionRequestDTO)
}