package com.hacka.presenteperfeito.core.common

/**
 * [FirstLoading] typically used for active fullscreen loadings
 * [Processing] typically used for active progress bars or buttons circular loading states.
 * [FirstLoadingError] typically user for show to UI the error and pop the UI.
 * [Complete] loading state is not showing in UI
 */
enum class Loading {
    FirstLoading,
    FirstLoadingError,
    Processing,
    Complete
}