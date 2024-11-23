package com.hacka.presenteperfeito.feature.home.data.service

import com.hacka.presenteperfeito.feature.home.data.model.GetInvestigationListItemResDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("investigation/list")
    suspend fun getInvestigationList(@Query("page") page: Int): List<GetInvestigationListItemResDTO>
}