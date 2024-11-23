package com.hacka.presenteperfeito.feature.home.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.hacka.presenteperfeito.core.common.BaseViewModel
import com.hacka.presenteperfeito.core.common.Loading
import com.hacka.presenteperfeito.core.common.bottomNavigation.BottomNavItem
import com.hacka.presenteperfeito.feature.home.data.useCase.HomeUseCase
import com.hacka.presenteperfeito.feature.home.presentation.uiState.HomeUiState
import com.hacka.presenteperfeito.feature.home.presentation.uiState.InvestigationItem
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(private val homeUseCase: HomeUseCase) : BaseViewModel<HomeUiState>(HomeUiState()) {

    init {
        fetchList()
    }

    private fun fetchList() {
        viewModelScope.launch {
            setLoadingState(Loading.FirstLoading)
            homeUseCase.getInvestigationList().catch {

            }.collect { res ->
                setState { state ->
                    state.copy(listOfInvestigations = res.map {  item ->
                        InvestigationItem(id = item.id, item.state, name = item.name)
                    })
                }
            }
            setLoadingState(Loading.Complete)
        }
    }

}