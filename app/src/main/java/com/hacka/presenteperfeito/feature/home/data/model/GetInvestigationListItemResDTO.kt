package com.hacka.presenteperfeito.feature.home.data.model

import com.hacka.presenteperfeito.feature.home.presentation.screen.components.ProgressStatus
import com.squareup.moshi.Json

data class GetInvestigationListItemResDTO(
    @Json(name = "id") val id: String,
    @Json(name = "investigationState") val state: ProgressStatus,
    @Json(name = "name") val name: String
)