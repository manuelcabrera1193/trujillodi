package com.cabrera.manuel.trujillodi.screenb

import androidx.compose.ui.graphics.Color
import com.cabrera.manuel.trujillodi.base.ui.UiState
import com.cabrera.manuel.trujillodi.util.getRandomComposeColor

data class ScreenUiStateB(
    val color: Color = getRandomComposeColor(),
    val title: String = "Screen B",
    val description: String = "This is screen B",
) : UiState {
    override val id: Int
        get() = 2
}