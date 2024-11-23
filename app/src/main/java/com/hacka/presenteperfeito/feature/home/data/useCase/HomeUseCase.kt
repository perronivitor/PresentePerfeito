package com.hacka.presenteperfeito.feature.home.data.useCase

import com.hacka.presenteperfeito.feature.home.data.repository.HomeRepository
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class HomeUseCase(private val homeRepository: HomeRepository) {
    fun getInvestigationList(page: Int = 1) =
        flow { emit(homeRepository.fetchInvestigationList(page)) }
}