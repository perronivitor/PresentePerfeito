package com.hacka.presenteperfeito.feature.home.screen.components

import androidx.compose.ui.graphics.Color

enum class ProgressStatus(val label: String, val color: Color) {
    Finalized("Finalizado", Color.Green.copy(green = 0.5f)),
    Waiting("Aguardando", Color.Blue),
    Canceled("Cancelado", Color.Red)
}