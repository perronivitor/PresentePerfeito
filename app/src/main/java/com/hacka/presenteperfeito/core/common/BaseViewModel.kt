package com.hacka.presenteperfeito.core.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State : UiState>(initialState: State) : ViewModel() {
    internal val currentUiState = MutableStateFlow(initialState)
    var sdasd = MutableLiveData<String>()

    fun setState(state: (State) -> State) = currentUiState.update { state(currentUiState.value) }
}