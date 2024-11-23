package com.hacka.presenteperfeito.core.common

/**
 * [FirstLoading] typically used for active fullscreen loadings
 * [Processing] typically used for active progress bars or buttons circular loading states.
 * [Complete] loading state is not showing in UI
 */
enum class Loading {
    FirstLoading,
    Processing,
    Complete
}