package com.hacka.presenteperfeito.core.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State : UiState>(initialState: State) : ViewModel() {
    private val _currentUiState = MutableStateFlow(initialState)
    protected val currentUiState
        get() = _currentUiState.value
    val uiState = _currentUiState.asStateFlow()

    private val _loadingState = MutableStateFlow(Loading.Complete)
    val loadingState = _loadingState.asStateFlow()
    val currentLoadingState
        get() = _loadingState.value

    fun setState(state: (State) -> State) = _currentUiState.update(state)
    fun setLoadingState(state:Loading) = _loadingState.update { state }
}