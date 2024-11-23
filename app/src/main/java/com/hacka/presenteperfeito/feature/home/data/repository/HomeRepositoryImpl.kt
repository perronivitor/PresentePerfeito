package com.hacka.presenteperfeito.feature.home.data.repository

import com.hacka.presenteperfeito.feature.home.data.model.GetInvestigationListItemResDTO
import com.hacka.presenteperfeito.feature.home.data.service.HomeService
import org.koin.core.annotation.Single

@Single
class HomeRepositoryImpl(private val homeService: HomeService) :
    HomeRepository {
    override suspend fun fetchInvestigationList(page: Int): List<GetInvestigationListItemResDTO> =
        homeService.getInvestigationList(page)
}