package com.cabrera.manuel.trujillodi.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cabrera.manuel.trujillodi.base.ui.UiState

interface Screen {
    val route: String
    val state: UiState
    val emitterData: EmitterData

    @Composable
    fun CreateBody(modifier: Modifier)
}