package com.hacka.presenteperfeito.core.designSystem.components.buttons

sealed interface ProjectButtonTypes {
    data object Primary : ProjectButtonTypes
    data object Secondary : ProjectButtonTypes
}