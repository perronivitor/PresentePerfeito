package com.hacka.presenteperfeito.feature.home.presentation.uiState

import com.hacka.presenteperfeito.core.common.UiState
import com.hacka.presenteperfeito.feature.home.presentation.screen.components.ProgressStatus

data class HomeUiState(
    val listOfInvestigations: List<InvestigationItem> = emptyList(),
): UiState

data class InvestigationItem(val id: String, val state: ProgressStatus, val name: String)
