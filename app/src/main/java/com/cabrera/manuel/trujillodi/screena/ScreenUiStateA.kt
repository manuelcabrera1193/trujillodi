package com.cabrera.manuel.trujillodi.screena

import com.cabrera.manuel.trujillodi.base.ui.UiState

data class ScreenUiStateA(
    override val id: Int = 1,
    var showExtraText: Boolean = false,
    val title: String = "Screen A",
    val description: String = "This is screen A",
) : UiState