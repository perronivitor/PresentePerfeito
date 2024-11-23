package com.hacka.presenteperfeito.feature.home.presentation.screen.components

import androidx.compose.ui.graphics.Color
import com.squareup.moshi.FromJson

enum class ProgressStatus(val label: String, val color: Color) {
    Finalized("Finalizado", Color.Green.copy(green = 0.5f)),
    Waiting("Aguardando", Color.Blue),
    Canceled("Cancelado", Color.Red)
}

class ProgressStatusEnumAdapter {
    @FromJson
    fun fromJson(value: String): ProgressStatus = when (value) {
        "COMPLETE" -> ProgressStatus.Finalized
        "IN_PROGRESS" -> ProgressStatus.Waiting
        "CANCELED" -> ProgressStatus.Canceled
        else -> throw IllegalArgumentException()
    }
}