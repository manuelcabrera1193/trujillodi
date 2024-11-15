package com.cabrera.manuel.trujillodi.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cabrera.manuel.trujillodi.base.ui.UiEvents
import com.cabrera.manuel.trujillodi.base.ui.UiState

interface Screen {
    val state: UiState
    val events: UiEvents

    @Composable
    fun Create(modifier: Modifier)
}