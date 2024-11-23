package com.hacka.presenteperfeito.feature.home.data.repository

import com.hacka.presenteperfeito.feature.home.data.model.GetInvestigationListItemResDTO

interface HomeRepository {
    suspend fun fetchInvestigationList(page: Int = 1): List<GetInvestigationListItemResDTO>
}