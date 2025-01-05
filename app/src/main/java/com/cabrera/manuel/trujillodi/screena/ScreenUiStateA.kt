package com.cabrera.manuel.trujillodi.screena

import androidx.compose.ui.graphics.Color
import com.cabrera.manuel.trujillodi.base.ui.UiState
import com.cabrera.manuel.trujillodi.util.getRandomComposeColor

data class ScreenUiStateA(
    override val id: Int = 1,
    var showExtraText: Boolean = false,
    val title: String = "Screen A",
    val description: String = "This is screen A",
    val color: Color = getRandomComposeColor(),
) : UiState