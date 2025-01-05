package com.cabrera.manuel.trujillodi.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cabrera.manuel.trujillodi.base.ui.UiState
import com.cabrera.manuel.trujillodi.ui.toolbar.Toolbar
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState

interface Screen {
    val route: String
    val state: UiState
    val emitterData: EmitterData

    @Composable
    fun CreateToolbar(modifier: Modifier) {
        Toolbar(ToolbarState())
    }

    @Composable
    fun CreateBody(modifier: Modifier)
}