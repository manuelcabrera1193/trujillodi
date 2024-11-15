package com.cabrera.manuel.trujillodi.screenb

import com.cabrera.manuel.trujillodi.base.ui.UiState

data class ScreenUiStateB(
    val title: String = "Screen B",
    val description: String = "This is screen B",
) : UiState {
    override val id: Int
        get() = 2
}