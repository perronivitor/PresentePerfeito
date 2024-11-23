package com.hacka.presenteperfeito.feature.inspection.presentation.uiState

import androidx.annotation.IntegerRes
import com.hacka.presenteperfeito.core.common.UiState

data class InspectionUiState(
    val name: String = "",
    @IntegerRes
    val nameError: Int? = null,
    @IntegerRes
    val relationshipError: Int? = null,
    val relationship: String = "",
    @IntegerRes
    val phoneNumberError: Int? = null,
    val phoneNumber: String = "",
    @IntegerRes
    val occasionError: Int? = null,
    val occasion: String = "",
    val hasError: Boolean = false,
    val event: InspectionFormEvent? = null,
) : UiState