package com.cabrera.manuel.trujillodi.base.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

open class CustomState<T: UiState>(initialValue: T) {
    private var stateMutable: MutableState<T> = mutableStateOf(initialValue)
    val state: State<T>
        get() = stateMutable

    internal fun updateState(newState: T) {
        stateMutable.value = newState
    }
}