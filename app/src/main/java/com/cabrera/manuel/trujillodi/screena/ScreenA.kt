package com.cabrera.manuel.trujillodi.screena

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.screena.view.ListRecipes

class ScreenA(
    override val state: ScreenUiStateA,
    override val emitterData: EmitterData,
    override val route: String = "screenA",
) : Screen {

    @Composable
    override fun CreateBody(modifier: Modifier) {
        ListRecipes(data = state)
    }
}